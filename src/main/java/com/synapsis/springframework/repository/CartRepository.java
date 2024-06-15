package com.synapsis.springframework.repository;

import com.synapsis.springframework.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {

  @Query("SELECT CA FROM Cart CA "
      + "WHERE CA.user.id = :userId")
  Optional<Cart> getCartByUserId(String userId);

}
