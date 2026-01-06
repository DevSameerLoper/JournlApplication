package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repositry.UserEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserEntryRepo userRepository;


    public void saveNewUser(Users user) {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public void saveUser(Users users){
        userRepository.save(users);
    }


    public List<Users> getAll() {
        return userRepository.findAll();
    }

    public Users findByUserName(String userName) {
        return userRepository.findByUserName(userName);


    }

    public void deleteByName(String name) {
        userRepository.deleteByUserName(name);

    }

    public void saveAdmin(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}
