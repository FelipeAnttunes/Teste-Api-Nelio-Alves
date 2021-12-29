package com.example.backend.testApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.testApi.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {

}
