package com.vkiprono.usersregistration.services;

import com.vkiprono.usersregistration.models.User;
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

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
         return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(User user){

        userRepository.delete(user);
    }

}
