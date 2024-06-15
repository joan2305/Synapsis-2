package com.synapsis.springframework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SynapsisUser {

  private String id;

  private String username;

  private String email;

  private String phoneNumber;

  private String password;

  private List<Order> orders = new ArrayList<Order>();
}
