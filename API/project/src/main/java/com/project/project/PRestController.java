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
    @GetMapping("/Ubyid")
    public User allUI(int id) {
        return databaseService.getUser(id) ;
    } 
    @GetMapping("/Nbyid")
    public Nota allNI(int id) {
        return databaseService.getNota(id) ;
    }     
   

    @PutMapping("/UpdateUbyid")
    public void updateU(String username,String email, String password, int id) {
        User user = new User(id, username, email,password );
        databaseService.updateUser(user) ;
    }

    @PutMapping("/UpdateNbyid")
    public void updateN(int UserID, String Title, String Content, int id) {

        Nota nota = new Nota(id, UserID, Title,Content );
        databaseService.updateNota(nota) ;
    }
    
    @PostMapping("/newuser")
    public void insertU(String username,String email, String password) {
        User user = new User(0, username, email, password);
        databaseService.insertUser(user) ;
    }

    @PostMapping("/newnote")
    public void insertN(int UserID, String Title, String Content) {
        Nota nota = new Nota(0, UserID, Title, Content);
        databaseService.insertNota(nota) ;
    }

    @PostMapping("/login")
    public User loginUser(String username, String password) {

    User  tmpUser =  databaseService.authenticateUser(username,password) ;
    tmpUser.setJTW();
    return tmpUser;
    }


    @DeleteMapping("/DeleteUbyid")
    public void deleteU(int id) {
        databaseService.deleteUser(id) ;
    }
    @DeleteMapping("/DeleteNbyid")
    public void deleteN(int id) {
        databaseService.deleteNota(id) ;
    }
}
