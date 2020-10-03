package com.hpst.testcontainer.demo;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


/**
 * 
 * Integration Test Using Test Containers
 * @author harpal
 *
 */
@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)

public class TestMySqlTestContainerIT {

    // will be shared between test methods
    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>();


   @BeforeAll
   public void startMySqlContainer() {
		MY_SQL_CONTAINER.start();
   }
    
    @Test
    @DisplayName("isMsqlContainerRunning")
    void isMsqlContainerRunning() {
    	assertTrue(MY_SQL_CONTAINER.isRunning());
    }
    
    @After
    void stopContainer() {
    	MY_SQL_CONTAINER.stop();
    }
}