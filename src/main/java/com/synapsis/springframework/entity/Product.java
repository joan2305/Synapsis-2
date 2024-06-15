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
@Table(name = "Product")
public class Product {

  @Id
  @Column(name = "ID", length = 36)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "USERNAME", length = 255)
  private String productName;

  @Column(name = "PRICE")
  private Integer price;

  @Column(name = "STOCK")
  private Integer stock;

  @Column(name = "PRODUCT_DESCRIPTION")
  private String productDescription;

  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;
}
