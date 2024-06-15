package com.synapsis.springframework.converter;

import com.synapsis.springframework.models.Category;
import com.synapsis.springframework.models.Product;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class ProductUtil {

  public static Product toWebModel(com.synapsis.springframework.entity.Product product){
    Product model = new Product();
    BeanUtils.copyProperties(product, model);
    Category category = new Category();
    BeanUtils.copyProperties(Optional.ofNullable(product)
        .map(com.synapsis.springframework.entity.Product::getCategory)
        .orElseGet(com.synapsis.springframework.entity.Category::new), category);
    model.setCategory(category);
    return model;
  }
}
