/**
 * 
 */
package com.hpst.testcontainer.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author harpal
 *
 */
@Service
public class UserService {

	@Autowired
	private UserJpaRepository userRepo;
	
	
	public List<UserDto> getUserList(){
		return userRepo.getUsers();
	}


	/**
	 * @return
	 */
	public void  saveUser() {
		 userRepo.save(new User(1l, "Harpal"));
	}
}
