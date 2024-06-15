package com.synapsis.springframework.repository;

import com.synapsis.springframework.entity.SynapsisUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SynapsisUserRepository extends JpaRepository<SynapsisUser, String> {

  Boolean existsByEmailOrUsernameOrPhoneNumber(String email, String username, String phoneNumber);

  Optional<SynapsisUser> findByUsername(String username);
}
