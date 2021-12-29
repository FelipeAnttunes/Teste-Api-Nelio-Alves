package com.example.backend.testApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.testApi.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

}
