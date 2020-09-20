package com.example.springbootsda.repository;

import com.example.springbootsda.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private List<User> users = new ArrayList<>();

  public void save(User user) {
    users.add(user);
  }

  public User get(Long id) {
    return users.stream().filter(x -> x.getId() == id).findFirst().orElseThrow();
  }

  public List<User> getAll() {
    return users;
  }

}
