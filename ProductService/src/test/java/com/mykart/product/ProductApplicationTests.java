package com.mykart.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mykart.product.dto.request.ProductRequest;
import com.mykart.product.service.ProductManagementService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class ProductApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductManagementService productManagementService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateProduct() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateInString = "7-Jun-2017";
        Date date = formatter.parse(dateInString);
        ProductRequest productRequest = ProductRequest.builder()
                .name("Lenovo Yoga")
                .description("Lenovo Yoga is a powerful foldable 14 inch laptop with 16GB RAM and 512 GB SSD.")
                .keywords(Arrays.asList("Lenovo", "Foldable", "14 inch", "16GB RAM", "512GB SSD"))
                .originalPrice(BigDecimal.valueOf(132000.0))
                .netPrice(BigDecimal.valueOf(135462.0))
                .manufacturedDate(date)
                .build();

        mockMvc.perform(post("/api/v1/product/manage/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(productRequest)))
                .andExpect(status().isCreated());
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}