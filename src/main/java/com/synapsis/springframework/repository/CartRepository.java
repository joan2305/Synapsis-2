package com.synapsis.springframework.repository;

import com.synapsis.springframework.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, String> {

}
