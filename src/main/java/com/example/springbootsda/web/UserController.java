package com.example.springbootsda.web;

import com.example.springbootsda.model.User;
import com.example.springbootsda.repository.UserRepository;
import com.example.springbootsda.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path = "api")
public class UserController {

  UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(path = "ok", method = RequestMethod.GET)
  public ResponseEntity<String> ok() {
    // tworzenie ResponseEntity przy pomocy buildera ze stausem 200 i body ze stringiem
    return ResponseEntity.ok().body("ok");
  }

  @RequestMapping(path = "no-content", method = RequestMethod.GET)
  public ResponseEntity<HttpStatus> noContent() {
    // tworzenie ResponseEntity ze statusem 204 za pomocą konstruktora
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(path = "user", method = RequestMethod.GET)
  public ResponseEntity<User> getUser() {
    User adam = new User("Adam", "Kowalski", LocalDate.now(),new BigDecimal("3000.00"));
    return ResponseEntity.ok().body(adam);
  }

  @RequestMapping(path = "user2", method = RequestMethod.GET)
  @ResponseBody
  public User getUser2() {
    User adam = new User("Adam", "Kowalski", LocalDate.now(),new BigDecimal("3000.00"));
    return adam;
  }

  @RequestMapping(path = "users", method = RequestMethod.GET) //Accept application/xml
  @ResponseBody
  public List<User> getUsers() {
    User adam = new User("Adam", "Kowalski", LocalDate.now(),new BigDecimal("3000.00"));
    User anna = new User("Anna", "Kowalska", LocalDate.now(),new BigDecimal("3000.00"));
    return List.of(adam, anna);
  }

  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, path = "hello-world")
  public String helloWorld() {
    return "hello-world";
  }

  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, path = "user/{number}")
  public String sayNumber(
      @PathVariable(name = "number") Integer number) { //name = "number" opcjonalne bo {number}
    return "Wpisałeś: " + number;
  }

  @ResponseBody
  @RequestMapping(value = "user", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@RequestBody User user) {
     userService.save(user);
    return user;
  }

  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, path = "users-path/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserPath(@PathVariable(name = "id") Long id) {
    return userService.get(id);
  }

  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, path = "user-param-new")
  @ResponseStatus(HttpStatus.OK)
  public User getUserParam(@RequestParam(name = "id") Long id) { // Nie stosujemy id w RequestParam, tu dla przykładu
    return userService.get(id);
  }

  @RequestMapping(path = "users-from-repo", method = RequestMethod.GET)
  @ResponseBody
  public List<User> getUsersFromRepo() {
    return userService.getAll();
  }

  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, path = "users-param") //?param1=val1&param2=val2
  @ResponseStatus(HttpStatus.OK)
  public String getUserParam(
      @RequestParam(value = "param1") String param1,
      @RequestParam(value = "param2", required = false) String param2
  ) {
    return String.format("Podałeś parametry: param1: %s, param2: %s", param1, param2);
  }
}
