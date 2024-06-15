package com.synapsis.springframework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cart_Item")
public class CartItem {

  @Id
  @Column(name = "ID", length = 36)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @ManyToOne
  @JoinColumn(name = "CART_ID")
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product product;

  @Column(name = "QUANTITY")
  private Integer quantity;
}
