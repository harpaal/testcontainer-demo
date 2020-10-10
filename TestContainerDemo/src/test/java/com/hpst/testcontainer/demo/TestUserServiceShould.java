/**
 * 
 */
package com.hpst.testcontainer.demo;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author harpal
 *
 */

@ExtendWith(MockitoExtension.class)
class TestUserServiceShould {

	@Mock
	UserJpaRepository userJpaRepo;

	@InjectMocks
	UserService userService;
	
	@Mock
	RestTemplate restTemplate;
	
	
	@BeforeEach
	public void setProperties() {
		//Can't use spring Context here since its mock test for Service class not integration test 
		//So @Value wont load properties , but spring provides ReflectionTestUtils to inject it manually 
		ReflectionTestUtils.setField(userService, "userPrefix", "Hello,");
		ReflectionTestUtils.setField(userService, "randomJokesApi", "https://official-joke-api.appspot.com/random_joke");
		
		

	}

	
	@Test
	void getRandomUser() {
		when(restTemplate.getForObject("https://official-joke-api.appspot.com/random_joke", String.class)).thenReturn("Jokes");
		assertEquals("Jokes",userService.getRandomJokes());
	}
	
	
	@Test
	void saveUser() {
		User newUser = new User(1l, "Hello,Harpal");
		when(userJpaRepo.save(newUser)).thenReturn(newUser);
		assertEquals("Hello,Harpal", userService.saveUser("Harpal").getName());

	}

	@Test
	void returnUserList() {
		List<UserDto> listOfUsers = prepareUserList();
		when(userJpaRepo.getUsers()).thenReturn(listOfUsers);
		assertTrue(userService.getUserList().size() > 0);

	}

	
	private List<UserDto> prepareUserList() {
		UserDto newUser = new UserDto(1l, "Harpal");
		List<UserDto> listOfUsers = new ArrayList<>();
		listOfUsers.add(newUser);
		return listOfUsers;
	}

}
