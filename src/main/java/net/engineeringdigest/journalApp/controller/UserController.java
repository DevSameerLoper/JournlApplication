package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;





    @GetMapping
    public List<Users> getAll() {
        return userService.getAll();
    }


    @PutMapping()
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }


    @DeleteMapping()
    public ResponseEntity<Users> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }




}
