/**
 * 
 */
package com.hpst.testcontainer.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author harpal
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/get-users")
	public List<UserDto> getUsers(){
		return userService.getUserList();
	}
	
	
	@PutMapping("/add-users")
	public void addUsers(String name){
		 userService.saveUser(name);
	}


}