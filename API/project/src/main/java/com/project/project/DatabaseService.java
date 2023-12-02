package com.project.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

     public List<User> getAllUsers() {
        try {
            // Replace 'app_log' with your actual table name and adjust the query as needed
            String query = "SELECT * FROM users";
            List<Map<String, Object>> resultNotas = jdbcTemplate.queryForList(query);
            List<User> users = new ArrayList<>();

            for (Map<String, Object> row : resultNotas) {
                int userID = (int) row.get("UserID");
                String username =(String) row.get("Username");
                String password =(String) row.get("Password");
                String email = (String) row.get("Email");
                User user = new User(userID,username,email,password);
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            // Handle exceptions if needed
            e.printStackTrace();
            return null;
        }
    }

     public User authenticateUser(String username, String password) {
        System.out.println("logId = " + username);
        try {
            String query = "SELECT * FROM proyecto.users WHERE UserName = ? and UserPassword =?";

            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int userID = (int)rs.getInt("UserID");
                String user_name = rs.getString("UserName");
                String user_password = rs.getString("UserPassword");
                String email = rs.getString("UserEmail");
              //   public User( String username,String email, String password) {
        
                return new User(userID, user_name,email, user_password);
            }, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUser(int id) {
        System.out.println("logId = " + id);
        try {
            String query = "SELECT * FROM users WHERE UserID = ?";

            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int userID = rs.getInt("UserID");
                String user_name = rs.getString("UserName");
                String user_password = rs.getString("UserPassword");
                String email = rs.getString("UserEmail");
                return new User(userID, user_name,email, user_password);
            }, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser(User user) {
        try {
            String query = "UPDATE users SET UserName = ?, UserPassword = ?, UserEmail = ? WHERE UserID = ?";
            jdbcTemplate.update(query, user.getUsername(), user.getUserpassword(), user.getEmail() , user.getUserID());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions if needed
        }
    }
    public void insertUser(User user) {
        try {
            String query = "INSERT  users SET UserName = ?, UserPassword = ?, UserEmail = ? ";
            jdbcTemplate.update(query, user.getUsername(),user.getUserpassword(),user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions if needed
        }
    }

    public int deleteUser(int id) {
        try {
            String query = "DELETE FROM users WHERE UserID = ?";
            jdbcTemplate.update(query, id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
            // Handle exceptions if needed
        }
    }










    public List<Nota> getAllNotas() {
        try {
            // Replace 'app_log' with your actual table name and adjust the query as needed
            String query = "SELECT * FROM notas";
            List<Map<String, Object>> resultProducts = jdbcTemplate.queryForList(query);
            List<Nota> notas = new ArrayList<>();

            for (Map<String, Object> row : resultProducts) {
                int NoteID = (int) row.get("NoteID");
                int UserID = (int) row.get("UserID");
                String Title = (String) row.get("Title");
                String Content = (String) row.get("Content");

                Nota nota = new Nota (NoteID, UserID, Title, Content);
                notas.add(nota);
            }
            return notas;
        } catch (Exception e) {
            // Handle exceptions if needed
            e.printStackTrace();
            return null;
        }
    }
    public Nota getNota(int id) {
        System.out.println("logId = " + id);
        try {
            String query = "SELECT * FROM notas WHERE NoteID = ?";

            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int NoteID = rs.getInt("NoteID");
                int UserID = (int) rs.getInt("UserID");
                String Title = rs.getString("Title");
                String Content = rs.getString("Content");

                return new Nota (NoteID, UserID, Title, Content);
            }, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateNota(Nota nota) {
        try {
            String query = "UPDATE notas SET UserID = ?, Title = ?, Content = ? WHERE NoteID = ?";
            jdbcTemplate.update(query, nota.getUserID(),nota.getTitle(), nota.getContent(), nota.getNoteID());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions if needed
        }
    }
    public void insertNota(Nota nota) {
        try {
            String query = "INSERT  proyecto.notas SET UserID = ?, Title = ?, Content = ? ";
            jdbcTemplate.update(query, nota.getUserID(),nota.getTitle(), nota.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions if needed
        }
    }

    public int deleteNota(int id) {
        try {
            String query = "DELETE FROM notas WHERE NoteID = ?";
            jdbcTemplate.update(query, id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
            // Handle exceptions if needed
        }
    }

    
}

