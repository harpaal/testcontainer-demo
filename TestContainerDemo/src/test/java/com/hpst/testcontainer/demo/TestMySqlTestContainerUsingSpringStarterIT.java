package com.hpst.testcontainer.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Using playtika springboot wrapper you dont have to explicitly start/stop the container
 * wrapper (emdedded-mysql.jar) does it for you.
 * 
 * @author Harpal Singh
 *
 */
@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TestMySqlTestContainerUsingSpringStarterIT  {

	@Container
	private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>();

	@Autowired
	private UserJpaRepository userRepo;

	@Test
	@DisplayName("Insert User Test")
	void testInsertUser() {
		User savedUser = userRepo.save(new User(1l, "Harpal"));
		assertEquals(savedUser.getName(), "Harpal");
	}
}
