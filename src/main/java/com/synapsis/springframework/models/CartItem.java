package com.synapsis.springframework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

  private String id;

  private Cart cart;

  private Product product;

  private Integer quantity;
}
