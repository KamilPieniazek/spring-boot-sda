package com.example.springbootsda.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //localhost:8080/api
@RequestMapping(path = "api")
public class HelloWorldController {

  @ResponseBody //localhost:8080/api/hello
  @RequestMapping(path = "hello", method = RequestMethod.GET)
  public String getHello() {
    return "Hello";
  }

}
