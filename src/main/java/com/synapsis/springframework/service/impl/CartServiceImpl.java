package com.synapsis.springframework.service.impl;

import com.synapsis.springframework.converter.CartItemUtil;
import com.synapsis.springframework.converter.OrderItemUtil;
import com.synapsis.springframework.entity.Cart;
import com.synapsis.springframework.entity.Order;
import com.synapsis.springframework.entity.OrderItem;
import com.synapsis.springframework.entity.Product;
import com.synapsis.springframework.entity.SynapsisUser;
import com.synapsis.springframework.models.CartItem;
import com.synapsis.springframework.repository.CartItemRepository;
import com.synapsis.springframework.repository.CartRepository;
import com.synapsis.springframework.repository.OrderItemRepository;
import com.synapsis.springframework.repository.OrderRepository;
import com.synapsis.springframework.repository.ProductRepository;
import com.synapsis.springframework.repository.SynapsisUserRepository;
import com.synapsis.springframework.request.CartItemRequest;
import com.synapsis.springframework.request.CheckoutRequest;
import com.synapsis.springframework.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartItemRepository cartItemRepository;

  @Autowired
  private SynapsisUserRepository synapsisUserRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Override
  public List<CartItem> getCartProducts(String userId){
    return cartItemRepository
        .getCartProducts(userId)
        .stream()
        .map(CartItemUtil::toWebModel)
        .collect(Collectors.toList());

  }

  @Override
  public Boolean updateCart(CartItemRequest request) {
    Optional<Cart> userCart = cartRepository.getCartByUserId(request.getUserId());
    if(userCart.isPresent()){
      Optional<com.synapsis.springframework.entity.CartItem> existing = findCartItem(request, userCart);
      if(existing.isPresent()){
        existing.get().setQuantity(request.getQty());
        cartItemRepository.save(existing.get());
      } else {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(NoSuchElementException::new);
        cartItemRepository.save(createCartItem(request, userCart.get(), product));
      }

    } else {
      Cart newCart = new Cart();
      SynapsisUser user = synapsisUserRepository.findById(request.getUserId()).orElseThrow(NoSuchElementException::new);
      Product product = productRepository.findById(request.getProductId()).orElseThrow(NoSuchElementException::new);
      newCart.setUser(user);
      newCart = cartRepository.save(newCart);

      com.synapsis.springframework.entity.CartItem cartItem = createCartItem(request, newCart, product);
      cartItemRepository.save(cartItem);
    }
    return Boolean.TRUE;
  }


  @Override
  public Boolean deleteProductFromCart(CartItemRequest request) {
    Optional<Cart> userCart = cartRepository.getCartByUserId(request.getUserId());
    if(userCart.isPresent()){
      Optional<com.synapsis.springframework.entity.CartItem> cartItem = findCartItem(request, userCart);
      if(cartItem.isPresent()){
        cartItemRepository.delete(cartItem.get());
      } else {
        throw new NoSuchElementException();
      }
    } else {
      throw new NoSuchElementException();
    }
    return Boolean.TRUE;
  }

  @Override
  public String checkout(CheckoutRequest request) {
    Optional<Cart> userCart = cartRepository.getCartByUserId(request.getUserId());
    if(userCart.isPresent()){
      SynapsisUser user = synapsisUserRepository.findById(request.getUserId()).orElseThrow(NoSuchElementException::new);
      List<com.synapsis.springframework.entity.CartItem> cartItems = cartItemRepository
          .findByIdIn(request.getIds());
      Order newOrder = Order.builder()
          .user(user)
          .date(new Date())
          .paymentAmount(request.getPaymentAmount())
          .build();
      Order saved = orderRepository.save(newOrder);
      List<OrderItem> orderItems = new ArrayList<>();
      cartItems
          .forEach(item -> orderItems.add(OrderItemUtil.toOrderItem(item, saved)));
      orderItemRepository.saveAll(orderItems);
      cartItemRepository.deleteAll(cartItems);
      return saved.getId();
    } else {
      throw new NoSuchElementException();
    }
  }

  private Optional<com.synapsis.springframework.entity.CartItem> findCartItem(CartItemRequest request,
      Optional<Cart> userCart) {
    List<com.synapsis.springframework.entity.CartItem> items = cartItemRepository.findByCart(userCart.get());
    Optional<com.synapsis.springframework.entity.CartItem> existing =
        items.stream().filter(i -> i.getProduct().getId().equals(request.getProductId())).findFirst();
    return existing;
  }

  private com.synapsis.springframework.entity.CartItem createCartItem(CartItemRequest request, Cart newCart, Product product) {
    return com.synapsis.springframework.entity.CartItem
        .builder()
        .cart(newCart)
        .product(product)
        .quantity(request.getQty())
        .build();
  }

}
