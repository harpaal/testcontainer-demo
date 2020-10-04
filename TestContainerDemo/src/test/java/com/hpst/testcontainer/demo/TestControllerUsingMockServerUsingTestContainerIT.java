/**
 * 
 */
package com.hpst.testcontainer.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * @author harpal
 *
 */
@Testcontainers
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@MockServerSettings(perTestSuite = true)
class TestControllerUsingMockServerUsingTestContainerIT {

	
	@Container
	MockServerContainer mockServerContainer = 	new MockServerContainer("5.10.0");

	MockServerClient mockserver;
	
	
	@BeforeAll
	void setUpMockServer() {
		mockServerContainer.start();
		mockserver=new MockServerClient(mockServerContainer.getHost(), 
				mockServerContainer.getServerPort());
		
	}
	


	@AfterAll
	void stopMockServer() {
		mockServerContainer.stop();
		mockserver.stopAsync();
	
	}
	
	
	
	

	@Test
	void testSetUsers() throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		
		User user= new User();
		user.setId(1l);
		user.setName("Harpal");
		
		String expectedResponse=objectMapper.writeValueAsString(user);
		System.out.println("expected---"+expectedResponse);
		assertNotNull(expectedResponse);
		mockserver.when(HttpRequest.
				request().withMethod("PUT")
				.withPath("/add-users")
				.withBody(JsonBody.json(expectedResponse))).
			    respond(HttpResponse.response().withStatusCode(200));
			   
	
	
	}
	
	
	@Test
	void testGetUsers() throws JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		
		User user= new User();
		user.setId(1l);
		user.setName("Harpal");
		
		String expectedResponse=objectMapper.writeValueAsString(user);
		System.out.println("expected---"+expectedResponse);
		assertNotNull(expectedResponse);
		mockserver.when(HttpRequest.
				request().withMethod("GET").withPath("/get-user")).
			    respond(HttpResponse.response().withStatusCode(200)
					   .withHeader(new Header("Content-Type","application/json; charset=utf-8"))
					   .withBody(JsonBody.json(expectedResponse)));
			   
	
	
	}
	
	
}
