package com.user.service.controller;

import com.user.service.entities.UserEntity;
import com.user.service.models.Motorcycle;
import com.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>>listUser(){
        List<UserEntity>users=userService.getAllUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Integer id){
        UserEntity user= userService.getUserById(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping
    public ResponseEntity<UserEntity>saveUser(@RequestBody UserEntity userEntity){
        UserEntity user= userService.saveUser(userEntity);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/motorcycles/{userId}")
    public ResponseEntity<List<Motorcycle>>getMotorcycles(@PathVariable Integer userId){
        UserEntity userEntity=userService.getUserById(userId);
        if(userEntity==null){
            return ResponseEntity.noContent().build();
        }
        List<Motorcycle>motorcycles=userService.getMotorcycles(userId);
        return ResponseEntity.ok(motorcycles);
    }

    @PostMapping("motorcycle/{userId}")
    public ResponseEntity<Motorcycle>saveMotorcycleInUser(@PathVariable Integer userId, @RequestBody Motorcycle motorcycle){
        Motorcycle newMotorcycle=userService.saveMotorcycle(userId, motorcycle);
        return ResponseEntity.ok(newMotorcycle);
    }
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>>getAllTheVehicles(@PathVariable Integer userId){
        Map<String, Object>result=userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }



}
