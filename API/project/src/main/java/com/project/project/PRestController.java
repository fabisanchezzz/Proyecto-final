package com.project.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PRestController {
    @Autowired
    private DatabaseService databaseService; // Inject DatabaseService here


    @GetMapping 
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/allUsers")
    public List<User> allU() {
        return databaseService.getAllUsers() ;
    }
    @GetMapping("/allNotes")
    public List<Nota> allN() {
        return databaseService.getAllNotas() ;
    }
    @GetMapping("/Ubyid") /////
    public User allUI(int id) {
        return databaseService.getUser(id) ;
    } 
    @GetMapping("/Nbyid")
    public Nota allNI(int id) {
        return databaseService.getNota(id) ;
    }     
   

    @PutMapping("/UpdateUbyid")
    public void updateU(String username,String email, String password, int id, String token) {
        if (valid(token, id)== false){
            return;
        } else {
        User user = new User(id, username, email,password );
        databaseService.updateUser(user) ;
    }}

    @PutMapping("/UpdateNbyid")
    public void updateN(int UserID, String Title, String Content, int id, String token) {
        if (valid(token, UserID)== false){
            return;
        } else {
        Nota nota = new Nota(id, UserID, Title,Content );
        databaseService.updateNota(nota);
    }
}
    @PostMapping("/newuser")
    public void insertU(String username,String email, String password) {
        User user = new User(0, username, email, password);
        databaseService.insertUser(user) ;
    }

    @PostMapping("/newnote")
    public void insertN(int UserID, String Title, String Content, String token) {
        if (valid(token, UserID)== false){
            return;
        } else {
        Nota nota = new Nota(0, UserID, Title, Content);
        databaseService.insertNota(nota) ;
    }
}

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


    @DeleteMapping("/DeleteUbyid")
    public void deleteU(int id, String token) {
        if (valid(token, id)== false){
            return;
        } else {
        databaseService.deleteNotaByU(id);
        databaseService.deleteUser(id);
    }}

    @DeleteMapping("/DeleteNbyid")
    public void deleteN(int id, String token) {
        Nota nota = databaseService.getNota(id);
          if (valid(token, nota.getUserID())== false){
            return;
        } else {
        databaseService.deleteNota(id) ;
    }}

    private boolean valid(String token, int UserID) {
        User user = databaseService.getUser(UserID);
        // Use equals() for string comparison
        return token.equals(user.getJWT());
    }
    
}    