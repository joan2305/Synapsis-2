package com.synapsis.springframework.service;

import com.synapsis.springframework.models.CartItem;
import com.synapsis.springframework.models.Product;

import java.util.List;

public interface CartService {
  List<CartItem> getCartProducts(String userId);

}
