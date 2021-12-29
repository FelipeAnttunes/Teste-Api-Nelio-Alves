package com.example.backend.testApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.testApi.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>  {

}
