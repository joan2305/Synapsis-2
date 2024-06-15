package com.synapsis.springframework.controller;


import com.synapsis.springframework.converter.ResponseUtil;
import com.synapsis.springframework.models.CartItem;
import com.synapsis.springframework.models.constant.ApiPath;
import com.synapsis.springframework.request.CartItemRequest;
import com.synapsis.springframework.request.CheckoutRequest;
import com.synapsis.springframework.response.Response;
import com.synapsis.springframework.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApiPath.CART)
public class CartController {

  @Autowired
  private CartService cartService;

  @RequestMapping(method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE,
      path = "/{userId}")
  public Response<List<CartItem>> getCart(@PathVariable String userId){
    List<CartItem> cartItems = cartService.getCartProducts(userId);
    return ResponseUtil.ok(cartItems);
  }

  @RequestMapping(method = RequestMethod.POST,
  produces = MediaType.APPLICATION_JSON_VALUE,
  consumes = MediaType.APPLICATION_JSON_VALUE, 
  path = "/{userId}")
  public Response<Boolean> addToCart(@PathVariable String userId, @Valid @RequestBody CartItemRequest request){
    request.setUserId(userId);
    Boolean result = cartService.updateCart(request);
    return ResponseUtil.ok(result);
  }

  @RequestMapping(method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = "/{userId}")
  public Response<Boolean> deleteProduct(@PathVariable String userId, @RequestBody CartItemRequest request){
    request.setUserId(userId);
    Boolean result = cartService.deleteProductFromCart(request);
    return ResponseUtil.ok(result);
  }

  @RequestMapping(method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = "/{userId}/checkout")
  public Response<String> checkout(@PathVariable String userId, @RequestBody CheckoutRequest request){
    request.setUserId(userId);
    String result = cartService.checkout(request);
    return ResponseUtil.ok(result);
  }

}
