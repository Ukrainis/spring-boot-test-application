package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Company;
import com.example.demo.entities.User;
import com.example.demo.exceptions.*;
import com.example.demo.helpers.*;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserRepository userRepo;
    private final CompanyService companyService;

    @Autowired
    public UsersService(UserRepository userRepository, CompanyService companyService) {
        userRepo = userRepository;
        this.companyService = companyService;
    }

    public List<User> returnAllUsers() {
        return userRepo.findAll();
    }

    public User returnUserById(Long id) {
        User user = userRepo.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

        return user;
    }

    public User returnUserByUserName(String userName) {
        User user = userRepo.findByUserName(userName)
            .orElseThrow(() -> new UserNotFoundException(userName));
        
        return user;
    }

    public Long addNewUser(User user){
        if(isUserInvalid(user)) {
            throw new InvalidUserDataException();
        }

        return userRepo.save(user).getId();
    }

    public void removeUserByUseName(String userName) {
        User user = userRepo.findByUserName(userName)
            .orElseThrow(() -> new UserNotFoundException(userName));
        
        userRepo.delete(user);        
    }

    public void addCompanyToUser(String userName, Company company) {
        if(companyService.isCompanyInvalid(company)) {
            throw new InvalidCompanyDataException();
        }
        
        User user = userRepo.findByUserName(userName)
            .orElseThrow(() -> new UserNotFoundException(userName));

        user.setCompany(company);
        user.getCompany().setUser(user);

        userRepo.save(user);
    }

    private boolean isUserInvalid(User user) {
        return (Utils.isEmpty(user.getName()) || Utils.isEmpty(user.getUserName()) 
            || Utils.isEmpty(user.getEmail()) || Utils.isEmpty(user.getPhone()) 
            || Utils.isEmpty(user.getWebsite()));
    }
}