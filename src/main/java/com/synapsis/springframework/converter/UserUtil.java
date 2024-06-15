package com.synapsis.springframework.converter;

import com.synapsis.springframework.entity.SynapsisUser;
import org.springframework.beans.BeanUtils;

public class UserUtil {

  public static SynapsisUser toEntity(com.synapsis.springframework.models.SynapsisUser user){
    SynapsisUser entity = new SynapsisUser();
    BeanUtils.copyProperties(user, entity, "id");
    return entity;
  }

  public static com.synapsis.springframework.models.SynapsisUser toModel(SynapsisUser user){
    com.synapsis.springframework.models.SynapsisUser model = new com.synapsis.springframework.models.SynapsisUser();
    BeanUtils.copyProperties(user, model);
    return model;
  }
}
