package com.synapsis.springframework.converter;

import com.synapsis.springframework.entity.Product;
import com.synapsis.springframework.models.CartItem;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class CartItemUtil {

  public static CartItem toWebModel(com.synapsis.springframework.entity.CartItem cartItem){
    CartItem model = new CartItem();
    BeanUtils.copyProperties(cartItem, model);
    model.setProduct(ProductUtil
        .toWebModel(Optional.ofNullable(cartItem)
            .map(com.synapsis.springframework.entity.CartItem::getProduct)
            .orElseGet(Product::new)));
    return model;
  }
}
