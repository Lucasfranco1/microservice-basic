package com.moto.service.repository;

import com.moto.service.entities.MotorcycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<MotorcycleEntity,Integer> {
    List<MotorcycleEntity>findMotorcycleByUserId(Integer userId);
}
