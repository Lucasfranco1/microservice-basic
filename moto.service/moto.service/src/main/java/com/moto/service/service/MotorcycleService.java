package com.moto.service.service;

import com.moto.service.entities.MotorcycleEntity;
import com.moto.service.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorcycleService {
    @Autowired
    private MotorcycleRepository motorcycleRepository;

    public List<MotorcycleEntity> getAllMotorcycles(){
        return motorcycleRepository.findAll();
    }
    public MotorcycleEntity getMotorcycleById(Integer id){
        return motorcycleRepository.findById(id).orElse(null);
    }
    public List<MotorcycleEntity>getMotorcycleByIdUser(Integer userId){
        return motorcycleRepository.findMotorcycleByUserId(userId);
    }
    public MotorcycleEntity saveMotorcycle(MotorcycleEntity motorcycleEntity){
        return motorcycleRepository.save(motorcycleEntity);
    }

}
