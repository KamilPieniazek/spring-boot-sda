package com.example.springbootsda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class User {

  private Long id;
  private String firstName;
  private String lastName;
  @JsonProperty("dataZatrudnienia")
  private LocalDate dateOfEmployment;

  private BigDecimal salary;

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public User(String firstName, String lastName, LocalDate dateOfEmployment,
      BigDecimal salary) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfEmployment = dateOfEmployment;
    this.salary = salary;
  }
}
