package com.synapsis.springframework.service;

import com.synapsis.springframework.models.SynapsisUser;

//@Service
public interface UserService {

  Boolean register(SynapsisUser user);

  SynapsisUser login(SynapsisUser user);
}
