package com.synapsis.springframework.converter;

import com.synapsis.springframework.response.Response;
import org.springframework.http.HttpStatus;

public class ResponseUtil {

  public static <T> Response<T> ok(T data){
    Response<T> response = new Response<>();
    response.setCode(HttpStatus.OK.value());
    response.setData(data);
    response.setStatus(HttpStatus.OK.name());
    return response;
  }
}
