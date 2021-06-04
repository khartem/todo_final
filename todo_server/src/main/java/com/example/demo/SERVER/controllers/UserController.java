package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.UserRepository;
import com.example.demo.SERVER.tables.Category;
import com.example.demo.SERVER.tables.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/addUser")
    User addUser(@RequestBody User user){
        System.out.println(user.toString());
        return this.userRepository.save(user);
    }

    @GetMapping("/getUser")
    User getUser(@PathVariable Long id){
        return this.userRepository.findUserById(id);
    }

    @GetMapping("/getUserAll")
    List<User> getUserAll(){
        return this.userRepository.findAll();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));

    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userUpdate) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setLogin(userUpdate.getLogin());
                    user.setSurname(userUpdate.getSurname());
                    user.setName(userUpdate.getName());
                    user.setFather_name(userUpdate.getFather_name());
                    user.setBirthday(userUpdate.getBirthday());
                    user.setCreated(userUpdate.getCreated());
                    user.setUpdated(userUpdate.getUpdated());
                    return userRepository.save(user);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }
}
