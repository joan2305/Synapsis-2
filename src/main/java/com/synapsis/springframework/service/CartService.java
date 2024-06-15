package com.synapsis.springframework.service;

import com.synapsis.springframework.models.CartItem;
import com.synapsis.springframework.models.Product;
import com.synapsis.springframework.request.CartItemRequest;
import com.synapsis.springframework.request.CheckoutRequest;

import java.util.List;

public interface CartService {
  List<CartItem> getCartProducts(String userId);

  Boolean updateCart(CartItemRequest request);

  Boolean deleteProductFromCart(CartItemRequest request);

  String checkout(CheckoutRequest request);

}
