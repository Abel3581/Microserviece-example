package com.microservice.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.repository.ProductRepository;
import com.microservice.productservice.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;

	private final String[] id = {"asd12","asadfa"};

	@DynamicPropertySource
	static void setProperties(@NotNull DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void createProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());

		Assertions.assertEquals(1, productRepository.findAll().size());

	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("name")
				.description("description")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

	@Test
	void getAllProducts() throws Exception {
			List<ProductResponse> responses = getProductResponse();
			String productResponseString = objectMapper.writeValueAsString(responses);
			mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productResponseString))
					.andExpect(status().isOk());
			Assertions.assertEquals(2, responses.size());

	}

	private List<ProductResponse> getProductResponse() {
		List<ProductResponse> responses = new ArrayList<>();
		for (String s : id) {
			responses.add(new ProductResponse(s, "nombre", "descriptions", BigDecimal.valueOf(1000)));

		}
		return responses;
	}

}
