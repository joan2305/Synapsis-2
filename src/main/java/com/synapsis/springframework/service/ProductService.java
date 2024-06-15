package com.synapsis.springframework.service;

import com.synapsis.springframework.models.Product;

import java.util.List;

//@Service
public interface ProductService {

  List<Product> getAllProducts(String category, String name);
}
