package com.project.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PRestController {
    @Autowired
    private DatabaseService databaseService; 

    @GetMapping 
    public String hello() {
        return "Hello, world!";
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all/Users")
    public List<User> allU() {
        return databaseService.getAllUsers() ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all/Notes")
    public List<Nota> allN() {
        return databaseService.getAllNotas() ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/id/User") /////
    public User allUI(int id) {
        return databaseService.getUser(id) ;
    } 

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/id/Note")
    public Nota allNI(int id) {
        return databaseService.getNota(id) ;
    }     
   
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/Update/User")
    public void updateU(String username,String email, String password, int id, String token) {
        if (valid(token, id)== false){
            return;
        } else {
        User user = new User(id, username, email,password );
        databaseService.updateUser(user) ;
    }}

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/Update/Note")
    public void updateN(int UserID, String Title, String Content, int id, String token) {
        if (valid(token, UserID)== false){
            return;
        } else {
        Nota nota = new Nota(id, UserID, Title,Content );
        databaseService.updateNota(nota);
    }
}

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/new/User")
    public void insertU(String username,String email, String password) {
        User user = new User(0, username, email, password);
        databaseService.insertUser(user) ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/new/Note")
    public void insertN(int UserID, String Title, String Content, String token) {
        if (valid(token, UserID)== false){
            return;
        } else {
        Nota nota = new Nota(0, UserID, Title, Content);
        databaseService.insertNota(nota) ;
    }
}
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public User loginUser(String username, String password) {

    User  tmpUser =  databaseService.authenticateUser(username,password) ;
    if (tmpUser == null){
        return null;
    }
    else {
    tmpUser.setJTW();
    return tmpUser;
}
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/Delete/User")
    public void deleteU(int id, String token) {
        if (valid(token, id)== false){
            return;
        } else {
        databaseService.deleteNotaByU(id);
        databaseService.deleteUser(id);
    }}

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/Delete/Note")
    public void deleteN(int id, String token) {
        Nota nota = databaseService.getNota(id);
          if (valid(token, nota.getUserID())== false){
            return;
        } else {
        databaseService.deleteNota(id) ;
    }}

    private boolean valid(String token, int UserID) {
        User user = databaseService.getUserL(UserID);
        return token.equals(user.getJWT());
    }

    //Metodo admin.
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all/Users/admin")
    public List<User> allUserAdmin() {
        return databaseService.getAllUsersAdmin() ;
    }

}    