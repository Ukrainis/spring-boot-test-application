package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.*;
import com.example.demo.exceptions.*;
import com.example.demo.helpers.*;
import com.example.demo.repositories.TodoRepository;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserRepository userRepo;
    private final AddressService addressService;
    private final GeoService geoService;
    private final CompanyService companyService;
    private final TodoRepository todoRepo;

    @Autowired
    public UsersService(UserRepository userRepository, CompanyService companyService, 
                        AddressService addressService, GeoService geoService, TodoRepository todoRepo) {
        this.userRepo = userRepository;
        this.addressService = addressService;
        this.geoService=geoService;
        this.companyService = companyService;
        this.todoRepo = todoRepo;
    }

    public List<User> returnAllUsers() {
        return userRepo.findAll();
    }

    public User returnUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return user;
    }

    public User returnUserByUserName(String userName) {
        User user = userRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));

        return user;
    }

    public Long addNewUser(User user) {
        if (isUserInvalid(user)) {
            throw new InvalidUserDataException();
        }

        String userName = user.getUserName();
        if(userRepo.findByUserName(userName).isPresent()) {
            throw new DublicateUserNameException(userName);
        }

        return userRepo.save(user).getId();
    }

    public void removeUserByUseName(String userName) {
        User user = userRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));

        userRepo.delete(user);
    }

    public void addAddressToUser(String userName, Address address) {
        if (addressService.isAddressInvalid(address)) {
            throw new InvalidAddressDataException();
        }

        User user = userRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));

        if(user.getAddress() == null){
            user.setAddress(address);
            user.getAddress().setUser(user);
        } else{
            user = addressService.updateUserAddress(user, address);
        }              

        userRepo.save(user);
    }

    public void addGeoToUserAddress(String userName, Geo geo) {
        if (geoService.isGeoInvalid(geo)) {
            throw new InvaliGeoException();
        }

        User user = userRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));

        if(user.getAddress() == null) {
            throw new MissingAddressException(userName);
        } else if (user.getAddress().getGeo() == null) {
            geo.setAddress(user.getAddress());
            user.getAddress().setGeo(geo);
        } else {
            geoService.updateGeoForUserAddress(user, geo);
        }

        userRepo.save(user);
    }

    public void addCompanyToUser(String userName, Company company) {
        if (companyService.isCompanyInvalid(company)) {
            throw new InvalidCompanyDataException();
        }

        User user = userRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));

        if(user.getCompany() == null){
            user.setCompany(company);
            user.getCompany().setUser(user);
        } else{
            user = companyService.updateUserCompany(user, company);
        }

        userRepo.save(user);
    }

    public void assignTodoToUser(String userName, Long todoId) {
        User user = userRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));
        Todo todo = todoRepo.findById(todoId).orElseThrow(() -> new TodoNotFoundException(todoId));

        todo.setUserId(user.getId());
        todoRepo.save(todo);
    }

    private boolean isUserInvalid(User user) {
        return (Utils.isEmpty(user.getName()) || Utils.isEmpty(user.getUserName()) || Utils.isEmpty(user.getEmail())
                || Utils.isEmpty(user.getPhone()) || Utils.isEmpty(user.getWebsite()));
    }
}