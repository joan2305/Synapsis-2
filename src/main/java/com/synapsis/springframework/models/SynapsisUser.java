package com.synapsis.springframework.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SynapsisUser {

  private String id;

  @NotBlank(message = "Username is mandatory")
  private String username;

  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Phone number is mandatory")
  private String phoneNumber;

  @NotBlank(message = "Password is mandatory")
  private String password;

  private List<Order> orders = new ArrayList<Order>();
}
