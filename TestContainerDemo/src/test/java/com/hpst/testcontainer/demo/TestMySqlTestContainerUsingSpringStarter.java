package com.hpst.testcontainer.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author harpal
 *
 */
@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TestMySqlTestContainerUsingSpringStarter {

	@Container
	private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>();

	@Autowired
	UserJpaRepository userRepo;

	@Test
	@DisplayName("Insert User Test")
	void testInsertUser() {
		User savedUser = userRepo.save(new User(1l, "Harpal"));
		assertEquals(savedUser.getName(), "Harpal");
	}
}

@Entity
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
class User {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "NAME", nullable = false)
	private String name;

}

@Repository
interface UserJpaRepository extends JpaRepository<User, Long> {
}
