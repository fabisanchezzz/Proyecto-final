package com.project.project;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {


    private int UserID;
    private String  Username;
    private String Email;
    private String Password;
    private String JWT;
    private String mySecreString= "Encriptado ProyectoFinal";
    public User() {
        this.UserID = 0;
        this.Username = "";
        this.Email = "";
        this.Password = "";
    }

        public User(int userID,String username,String email) {
        this.UserID = userID;
        this.Username = username;
        this.Email = email;
    }


    public User(int userID,String username,String email, String password) {
        this.UserID = userID;
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.JWT = generateJsonWebToken(username, password);
    }
   public User( String username,String email, String password) {
        
        this.Username = username;
        this.Email = email;
        this.Password = password;
        this.JWT = generateJsonWebToken(username, password);
  
    }
    public int getUserID() {
        return this.UserID;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getUsername() {
    return this.Username;
    }

    public String getUserpassword() {
    return this.Password;
    }

    public boolean checkPassword(String password) {
        return this.Password.equals(password);
    }

    public void setPassword(String password) {
        this.Password = password;
        setJTW();
    }   

    public void setJTW() {
        this.JWT = generateJsonWebToken(this.Username, this.Password);
    }  
   
    private String generateJsonWebToken(String username, String password) {
        try {
            // Concatenate username, password, and secret key
            String dataToHash = ""+username + password + mySecreString;
            System.out.println("jtw = " + username);
     
            // Use SHA-256 algorithm to hash the data
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

            // Convert hashed bytes to a hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            System.out.println("jtw = " + hexString.toString());
     
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; 
        }
    }

    public String getJWT() {
    return this.JWT;
    }
 

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(Username, user.Username) &&
               Objects.equals(Email, user.Email) &&
               Objects.equals(Password, user.Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Username, Email, Password);
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + Username + '\'' +
               ", email='" + Email + '\'' +
               ", password='" + Password + '\'' +
               '}';
    }

    
}
