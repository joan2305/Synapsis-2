package com.synapsis.springframework.repository;

import com.synapsis.springframework.entity.SynapsisUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSynapsisRepository extends JpaRepository<SynapsisUser, String> {

}
