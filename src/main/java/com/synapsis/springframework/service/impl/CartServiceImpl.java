package com.synapsis.springframework.service.impl;

import com.synapsis.springframework.converter.CartItemUtil;
import com.synapsis.springframework.converter.ProductUtil;
import com.synapsis.springframework.entity.Cart;
import com.synapsis.springframework.models.CartItem;
import com.synapsis.springframework.models.Product;
import com.synapsis.springframework.repository.CartItemRepository;
import com.synapsis.springframework.repository.CartRepository;
import com.synapsis.springframework.repository.ProductRepository;
import com.synapsis.springframework.service.CartService;
import com.synapsis.springframework.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartItemRepository cartItemRepository;

  @Override
  public List<CartItem> getCartProducts(String userId){
    return cartItemRepository
        .getCartProducts(userId)
        .stream()
        .map(CartItemUtil::toWebModel)
        .collect(Collectors.toList());

  }
}
