package com.synapsis.springframework.controller;


import com.synapsis.springframework.converter.ResponseUtil;
import com.synapsis.springframework.models.SynapsisUser;
import com.synapsis.springframework.models.constant.ApiPath;
import com.synapsis.springframework.response.Response;
import com.synapsis.springframework.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(ApiPath.USER)
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = "/register")
  public Response<Boolean> register(@RequestBody @Valid SynapsisUser user){
    Boolean result = userService.register(user);
    return ResponseUtil.ok(result);
  }

  @RequestMapping(method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      path = "/login")
  public Response<SynapsisUser> login(@RequestBody SynapsisUser user){
    SynapsisUser result = userService.login(user);
    return ResponseUtil.ok(result);
  }
}
