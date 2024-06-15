package com.synapsis.springframework.controller;


import com.synapsis.springframework.converter.ResponseUtil;
import com.synapsis.springframework.models.Product;
import com.synapsis.springframework.models.constant.ApiPath;
import com.synapsis.springframework.response.Response;
import com.synapsis.springframework.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApiPath.PRODUCTS)
public class ProductController {

  @Autowired
  private ProductService productService;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<List<Product>> list(@RequestParam(required = false, defaultValue = "") String category,
      @RequestParam(required = false, defaultValue = "") String categoryName){
    return ResponseUtil.ok(productService.getAllProducts(category, categoryName));
  }


}
