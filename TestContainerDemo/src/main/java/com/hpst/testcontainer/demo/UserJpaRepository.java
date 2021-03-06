package com.hpst.testcontainer.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface UserJpaRepository extends JpaRepository<User, Long> {
	
	@Query("select new com.hpst.testcontainer.demo.UserDto(u.id,u.name) from User u")
	public List<UserDto> getUsers();
}