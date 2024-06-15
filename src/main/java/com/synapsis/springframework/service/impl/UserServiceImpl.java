package com.synapsis.springframework.service.impl;

import com.synapsis.springframework.converter.UserUtil;
import com.synapsis.springframework.models.SynapsisUser;
import com.synapsis.springframework.repository.SynapsisUserRepository;
import com.synapsis.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

  private final String REDIS_PREFIX = "SYNAPSIS-USER:%s";
  private static final long CACHE_EXPIRATION = 1;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private SynapsisUserRepository repository;

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Override
  public Boolean register(SynapsisUser user) {
    if(repository.existsByEmailOrUsernameOrPhoneNumber(user.getEmail(), user.getUsername(), user.getPhoneNumber())){
      throw new IllegalStateException("User already registered");
    }
    com.synapsis.springframework.entity.SynapsisUser entity = UserUtil.toEntity(user);
    entity.setPassword(encoder.encode(entity.getPassword()));
    com.synapsis.springframework.entity.SynapsisUser saved = repository.save(entity);
    redisTemplate.opsForValue().set(String.format(REDIS_PREFIX, saved.getUsername()), saved, CACHE_EXPIRATION, TimeUnit.HOURS);
    return Boolean.TRUE;
  }

  @Override
  public SynapsisUser login(SynapsisUser user) {
    com.synapsis.springframework.entity.SynapsisUser synapsisUser =
        (com.synapsis.springframework.entity.SynapsisUser) redisTemplate.opsForValue().get(String.format(REDIS_PREFIX, user.getUsername()));
    if (Objects.isNull(synapsisUser)) {
      synapsisUser = repository.findByUsername(user.getUsername())
          .orElseThrow(() -> new IllegalStateException("User not found"));
      redisTemplate.opsForValue().set(String.format(REDIS_PREFIX, synapsisUser.getUsername()), synapsisUser, CACHE_EXPIRATION, TimeUnit.HOURS);
    }
    if(encoder.matches(user.getPassword(), synapsisUser.getPassword())){
      return UserUtil.toModel(synapsisUser);
    } else throw new IllegalStateException("Invalid password");
  }
}
