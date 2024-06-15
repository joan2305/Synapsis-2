package com.synapsis.springframework.converter;

import com.synapsis.springframework.entity.CartItem;
import com.synapsis.springframework.entity.Order;
import com.synapsis.springframework.entity.OrderItem;
import org.springframework.beans.BeanUtils;

public class OrderItemUtil {

  public static OrderItem toOrderItem(CartItem cartItem, Order order){
    OrderItem orderItem = new OrderItem();
    BeanUtils.copyProperties(cartItem, orderItem, "id");
    orderItem.setOrder(order);
    return orderItem;
  }
}
