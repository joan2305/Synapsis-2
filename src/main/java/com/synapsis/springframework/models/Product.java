package com.synapsis.springframework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private String id;

  private String productName;

  private Integer price;

  private Integer stock;

  private String productDescription;

  private Category category;
}
