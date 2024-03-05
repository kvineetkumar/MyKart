package com.mykart.cart.service;

import com.mykart.cart.dto.CartBillResponse;
import com.mykart.cart.dto.CartDto;
import com.mykart.cart.dto.UpdateCartDto;
import com.mykart.cart.entity.Cart;
import com.mykart.cart.entity.CartItem;
import com.mykart.cart.exception.ResourcesNotFoundException;
import com.mykart.cart.exception.StockNotAvailableException;
import com.mykart.cart.model.Product;
import com.mykart.cart.repository.CartItemRepository;
import com.mykart.cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartService {

    private static final String PRODUCT_URL = "http://product-service:8081/api/v1/product/";

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;


    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


    public Cart getCartById(String id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) return cartOptional.get();
        else throw new ResourcesNotFoundException("");
    }


    @Transactional
    public Cart createCart(CartDto cartDto) {
        Cart cart;
        if (cartDto instanceof UpdateCartDto) {
            var cartOptional = cartRepository.findById(((UpdateCartDto) cartDto).getCartId());
            cart = cartOptional.orElseGet(Cart::new);
        } else cart = new Cart();

        List<Product> products = getProductsFromIds(cartDto.getProductIds());
        var productIds = cartDto.getProductIds();

        if (products.size() == cartDto.getProductIds().size()) {
            productIds.forEach(productId -> {
                CartItem cartItem = new CartItem();
                cartItem.setProductId(productId);
                cartItem.setQuantity(1);
                cartItem.setCart(cart);
                cart.getCartItems().add(cartItem);
            });
            return cartRepository.save(cart);
        } else {
            return null;
        }
    }


    public Product getProductFromCartItem(String cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            var productId = cartItem.getProductId();
            List<Product> products = getProductsFromIds(Collections.singletonList(productId));
            if (!products.isEmpty())
                return products.get(0);
        }
        return null;
    }


    public void deleteCart(String id) {
        if (cartRepository.existsById(id))
            cartRepository.deleteById(id);
        else throw new ResourcesNotFoundException("");
    }


    public CartBillResponse getCartBill(String id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        List<CartBillResponse.CartItemResponse> cartItemResponses = new ArrayList<>();
        BigDecimal totalPrice = cartOptional.map(cart -> {
            var productIds = cart.getCartItems().stream().map(CartItem::getProductId).toList();

            //REST call to fetch products by ids from Product Catalog Service.
            var products = getProductsFromIds(productIds);

            //Filter out products unavailable in stock.
            var noStockProducts = products.stream().filter(product -> !product.isAvailable()).toList();
            if (!noStockProducts.isEmpty()) {
                throw new StockNotAvailableException(noStockProducts);
            }
            //products.removeIf(product -> !product.isAvailable());

            AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.valueOf(0.0));
            products.forEach(product -> {
                int gst = (int) (((product.getNetPrice().doubleValue() - product.getOriginalPrice().doubleValue()) * 100) / product.getOriginalPrice().doubleValue());
                cartItemResponses.add(new CartBillResponse.CartItemResponse(product.getName(), product.getOriginalPrice(), gst));
                total.set(total.get().add(product.getOriginalPrice()));
            });
            return total.get();
        }).orElseThrow(ResourcesNotFoundException::new);

        return new CartBillResponse(cartItemResponses, totalPrice);
    }

    public Cart updateCart(UpdateCartDto updateCartDto) {
        return createCart(updateCartDto);
    }

    private List<Product> getProductsFromIds(List<String> productIds) {
        String url = PRODUCT_URL + productIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        var response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        if (response.getBody() == null) return Collections.emptyList();
        var list = (List<LinkedHashMap>) response.getBody();
        List<Product> products = new ArrayList<>();
        list.forEach(map -> products.add(modelMapper.map(map, Product.class)));
        return products;
    }
}