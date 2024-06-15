package com.synapsis.springframework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  private String id;

  private Order order;

  private Product product;

  private Integer quantity;
}
