package com.example.springbootsda.service;

import com.example.springbootsda.model.User;
import com.example.springbootsda.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public User get(Long id) {
    return userRepository.get(id);
  }

  public List<User> getAll() {
    return userRepository.getAll();
  }

}
