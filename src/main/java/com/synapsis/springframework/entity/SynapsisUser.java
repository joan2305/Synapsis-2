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
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Synapsis_User")
public class SynapsisUser implements Serializable {

  @Id
  @Column(name = "ID", length = 36)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "USERNAME", length = 255, unique = true)
  private String username;

  @Column(name = "EMAIL", length = 255, unique = true)
  private String email;

  @Column(name = "PHONE_NUMBER", length = 15, unique = true)
  private String phoneNumber;

  @Column(name = "PASSWORD", length = 60)
  private String password;

//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  private List<Order> orders = new ArrayList<Order>();
}
