package com.vkiprono.usersregistration.services;

import com.vkiprono.usersregistration.models.UserReg;
import com.vkiprono.usersregistration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Vkiprono on 5/27/20 ---2:08 PM
 * @project usersregistration
 */
@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserReg> getAllUsers(){
        return userRepository.findAll();
    }

    public UserReg saveUser(UserReg userReg){
         return userRepository.save(userReg);
    }

    public Optional<UserReg> findUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(UserReg userReg){

        userRepository.delete(userReg);
    }

}
