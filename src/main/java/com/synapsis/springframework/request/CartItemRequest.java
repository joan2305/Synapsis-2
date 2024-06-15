package com.synapsis.springframework.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {

  private String userId;
  @NotNull
  private String productId;
  @NotNull
  private Integer qty;
}
