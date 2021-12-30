package com.example.backend.testApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.testApi.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>  {

}
