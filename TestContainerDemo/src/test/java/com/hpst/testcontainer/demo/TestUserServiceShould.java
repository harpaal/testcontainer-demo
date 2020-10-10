/**
 * 
 */
package com.hpst.testcontainer.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author harpal
 *
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TestUserServiceShould {

	@Mock
	UserJpaRepository userJpaRepo;

	@InjectMocks
	UserService userService;

	@BeforeEach
	public void setProperties() {
		ReflectionTestUtils.setField(userService, "userPrefix", "Hello,");

	}

	@Test
	void saveUser() {
		User newUser = new User(1l, "Hello,Harpal");
		when(userJpaRepo.save(newUser)).thenReturn(newUser);
		System.out.println("-->" + userService.saveUser("Harpal"));
		assertEquals("Hello,Harpal", userService.saveUser("Harpal").getName());

	}

	@Test
	void returnUserList() {

		List<UserDto> listOfUsers = prepareUserList();
		when(userJpaRepo.getUsers()).thenReturn(listOfUsers);
		System.out.println(userService.getUserList());
		assertTrue(userService.getUserList().size() > 0);

	}

	/**
	 * @return
	 */
	private List<UserDto> prepareUserList() {
		UserDto newUser = new UserDto(1l, "Harpal");
		List<UserDto> listOfUsers = new ArrayList<>();
		listOfUsers.add(newUser);
		return listOfUsers;
	}

}
