package com.project.project;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE) 
@ActiveProfiles("test")
class DatabaseServiceIntegrationTest {

    @Autowired
    private DatabaseService databaseService;

    @Test
    void testGetAllUsersFromDatabase() {
       
        List<User> actualUsers = databaseService.getAllUsers();

        
        assertTrue(actualUsers.size() >= 2, "Expected at least 2 records in the database, but found: " + actualUsers.size());
    }
    @Test
    void testAuthenticateUserFromDatabase() {
       
        String username = "user1";
        String password = "password1";
        User authenticatedUser = databaseService.authenticateUser(username, password);

        
        assertNotNull(authenticatedUser, "Authentication successful for user: " + username);
    }
    @Test
    void testFailedAuthenticateUserFromDatabase() {
        
        String username = "user112";
        String password = "password1";
        User authenticatedUser = databaseService.authenticateUser(username, password);

        
        assertNull(authenticatedUser, "Authentication failed for user: " + username);
    }

}
