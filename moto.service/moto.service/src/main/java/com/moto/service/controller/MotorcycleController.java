package com.moto.service.controller;

import com.moto.service.entities.MotorcycleEntity;
import com.moto.service.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motorcycle")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping
    public ResponseEntity<List<MotorcycleEntity>> listMotorcycles(){
        List<MotorcycleEntity>motorcycles=motorcycleService.getAllMotorcycles();
        if(motorcycles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcycles);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MotorcycleEntity> getMotorcycleById(@PathVariable Integer id){
        MotorcycleEntity motorcycle= motorcycleService.getMotorcycleById(id);
        if(motorcycle==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motorcycle);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MotorcycleEntity>>getMotorcyclesByUserId(@PathVariable Integer userId){
        List<MotorcycleEntity>motorcyclesByUser=motorcycleService.getMotorcycleByIdUser(userId);
        if(motorcyclesByUser.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motorcyclesByUser);
    }

    @PostMapping
    public ResponseEntity<MotorcycleEntity>saveMotorcycle(@RequestBody MotorcycleEntity motorcycleEntity){
        MotorcycleEntity motorcycle= motorcycleService.saveMotorcycle(motorcycleEntity);
        return ResponseEntity.ok(motorcycle);
    }


}
