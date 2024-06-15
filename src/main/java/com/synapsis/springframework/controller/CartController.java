package com.synapsis.springframework.controller;


import com.synapsis.springframework.converter.ResponseUtil;
import com.synapsis.springframework.models.CartItem;
import com.synapsis.springframework.models.Product;
import com.synapsis.springframework.models.constant.ApiPath;
import com.synapsis.springframework.response.Response;
import com.synapsis.springframework.service.CartService;
import com.synapsis.springframework.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApiPath.CART)
public class CartController {

  @Autowired
  private CartService cartService;

  @RequestMapping(method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE,
      path = "/{id}")
  public Response<List<CartItem>> getCart(@PathVariable String id){
    List<CartItem> cartItems = cartService.getCartProducts(id);
    return ResponseUtil.ok(cartItems);
  }

  @RequestMapping(method = RequestMethod.POST,
  produces = MediaType.APPLICATION_JSON_VALUE,
  consumes = MediaType.APPLICATION_JSON_VALUE)


}
