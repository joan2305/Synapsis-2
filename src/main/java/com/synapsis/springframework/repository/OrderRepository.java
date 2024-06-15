package com.synapsis.springframework.repository;

import com.synapsis.springframework.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {


}
