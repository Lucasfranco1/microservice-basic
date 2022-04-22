package com.user.service.service;

import com.user.service.entities.UserEntity;
import com.user.service.feignclients.MotorcycleFeignClient;
import com.user.service.models.Motorcycle;
import com.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MotorcycleFeignClient motorcycleFeignClient;


    public List<Motorcycle>getMotorcycles(Integer userId){
        List<Motorcycle>motorcycles=restTemplate.getForObject("http://motorcycle-service/motorcycle/user/"+ userId, List.class);
        return motorcycles;
    }

    public Motorcycle saveMotorcycle(Integer userId, Motorcycle motorcycle){
        motorcycle.setUserId(userId);
        return motorcycleFeignClient.save(motorcycle);
    }
    public Map<String, Object> getUserAndVehicles(Integer userId){
        Map<String, Object>result=new HashMap<>();
        UserEntity userEntity=userRepository.findById(userId).orElse(null);
        if(userEntity==null){
            result.put("Message", "The user does not exist");
            return result;
        }
        result.put("User", userEntity);
        List<Motorcycle>motorcycles=motorcycleFeignClient.getMotorcycles(userId);
        if(motorcycles.isEmpty()){
            result.put("Motorcycles", "The motorcycles not does exist.");
        }else{
            result.put("Motorcycles", motorcycles);
        }
        return result;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }
    public UserEntity getUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }
    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
}
