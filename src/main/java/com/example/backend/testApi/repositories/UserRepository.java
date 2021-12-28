package com.example.backend.testApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.testApi.entities.User;

public interface UserRepository extends JpaRepository<User, Long>  {

}
