package com.arrelin.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@LocalServerPort
	private Integer port;
	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() {

		String productRequest = """
				{
				"name": "cyka",
				"description": "dlya lohov",
				"price": "3200.01"
				}
			""";

		RestAssured.given()
				.body(productRequest)
				.header("Content-Type", "application/json")
				.post("/products")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("cyka"))
				.body("description", Matchers.equalTo("dlya lohov"))
				.body("price", Matchers.equalTo(3200.01f));
	}

}
