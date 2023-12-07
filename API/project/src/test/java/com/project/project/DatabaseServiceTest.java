package com.project.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.project.project.User;  
import org.springframework.jdbc.core.RowMapper;  


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

class DatabaseServiceTest {

    private DatabaseService databaseService;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        databaseService = new DatabaseService(jdbcTemplate);
    }

    @Test
    void testGetAllUsers() {
        when(jdbcTemplate.queryForList(
                eq("SELECT UserID, UserName, UserEmail FROM proyecto.users")
        )).thenReturn(Arrays.asList(
                createUserDataMap(1, "user1", "email1"),
                createUserDataMap(2, "user2", "email2")
        ));

        
        List<User> actualUsers = databaseService.getAllUsers();

        
        List<User> expectedUsers = Arrays.asList(
                new User(1, "user1", "email1"),
                new User(2, "user2", "email2")
        );

        
        assertEquals(expectedUsers, actualUsers);
    }

    private Map<String, Object> createUserDataMap(int userID, String username, String email) {
        Map<String, Object> userDataMap = new HashMap<>();
        userDataMap.put("UserID", userID);
        userDataMap.put("UserName", username);
        userDataMap.put("UserEmail", email);
        return userDataMap;
    }

    @Test
void testGetUser() {
    
    when(jdbcTemplate.queryForObject(
            eq("SELECT UserID, UserName, UserEmail FROM proyecto.users WHERE UserID = ?"),
            any(RowMapper.class), // Use any() for RowMapper
            eq(1)
    )).thenReturn(new User(1, "user1", "email1"));

   
    User actualUser = databaseService.getUser(1);

   
    User expectedUser = new User(1, "user1", "email1");

    
    assertEquals(expectedUser, actualUser);
}

   
    @Test
    void testInsertUser() {
       
        when(jdbcTemplate.update(
                "INSERT  proyecto.users SET UserName = ?, UserPassword = ?, UserEmail = ? ",
                "UserTest", "PasswordTest", "EmailTest")).thenReturn(1);

        
        User userToInsert = new User("UserTest", "EmailTest", "PasswordTest");
        databaseService.insertUser(userToInsert);

        
        verify(jdbcTemplate).update(
    "INSERT  proyecto.users SET UserName = ?, UserPassword = ?, UserEmail = ? ",
    "UserTest",
    "PasswordTest",
    "EmailTest"
        );
    
    }
    
    private Map<String, Object> createNoteDataMap(int NoteID, int UserID, String Title, String Content) {
        Map<String, Object> noteDataMap = new HashMap<>();
        noteDataMap.put("NoteID", NoteID);
        noteDataMap.put("UserID", UserID);
        noteDataMap.put("Title", Title);
        noteDataMap.put("Content", Content);
        return noteDataMap;
    }    
    
    @Test
    void testGetAllNotes() {
        when(jdbcTemplate.queryForList(
                eq("SELECT * FROM proyecto.notas")
        )).thenReturn(Arrays.asList(
                createNoteDataMap(1, 1, "Title1", "Este es un test"),
                createNoteDataMap(2, 2, "Title2", "Este es un test 2")
        ));

       
        List<Nota> actualNotes = databaseService.getAllNotas();

       
        List<Nota> expectedNotes = Arrays.asList(
                new Nota(1, 1, "Title1", "Este es un test"),
                new Nota(2, 2, "Title2", "Este es un test 2")
        );

       
        assertEquals(expectedNotes, actualNotes);
    }

@Test
    void testInsertNote() {
       
        when(jdbcTemplate.update(
                "INSERT  proyecto.notas SET UserID = ?, Title = ?, Content = ? ",
                1, "Title1", "Esto es un test")).thenReturn(1);

        
        Nota notaToInsert = new Nota (1, "Title1", "Esto es un test");
        databaseService.insertNota(notaToInsert);

        verify(jdbcTemplate).update(
    "INSERT  proyecto.notas SET UserID = ?, Title = ?, Content = ? ",
                1, "Title1", "Esto es un test"
        );
    
    }    

}