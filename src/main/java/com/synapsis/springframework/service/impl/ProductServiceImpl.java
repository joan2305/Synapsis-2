package com.synapsis.springframework.service.impl;

import com.synapsis.springframework.converter.ProductUtil;
import com.synapsis.springframework.models.Product;
import com.synapsis.springframework.repository.ProductRepository;
import com.synapsis.springframework.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;
  @Override
  public List<Product> getAllProducts(String category, String name) {
    List<com.synapsis.springframework.entity.Product> datas = productRepository
        .findByCategoryOrName(Optional.ofNullable(category)
            .filter(s -> !StringUtils.isEmpty(s))
            .orElse(""), Optional.ofNullable(name)
            .filter(s -> !StringUtils.isEmpty(s))
            .orElse(""));
    return datas
        .stream()
        .map(ProductUtil::toWebModel)
        .collect(Collectors.toList());
  }
}
