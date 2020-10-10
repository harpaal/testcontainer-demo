/**
 * 
 */
package com.hpst.testcontainer.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.ToString;

/**
 * @author harpal
 *
 */
@Service
@ToString
public class UserService {

	@Autowired
	private UserJpaRepository userRepo;
	
	@Value("user.prefix")
	private String userPrefix;
	
	public List<UserDto> getUserList(){
		return userRepo.getUsers();
	}


	/**
	 * @param user 
	 * @return
	 */
	public User  saveUser(String name) {
		User user = new User(1l, userPrefix+name);
		 return userRepo.save(user);
	}
}
