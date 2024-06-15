package com.synapsis.springframework.repository;


import com.synapsis.springframework.entity.Cart;
import com.synapsis.springframework.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, String> {

  @Query("SELECT CI FROM CartItem CI "
      + "JOIN CI.cart CA "
      + "JOIN CA.user US "
      + "WHERE US.id = :userId")
  List<CartItem> getCartProducts(String userId);

  List<CartItem> findByCart(Cart cart);

  List<CartItem> findByIdIn(List<String> ids);
}
