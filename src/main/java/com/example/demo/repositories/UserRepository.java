package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{
    
    @Query(value = "select * from users where user_name = :user_name", nativeQuery = true)
    Optional<User> findByUserName(@Param("user_name") String userName);
}