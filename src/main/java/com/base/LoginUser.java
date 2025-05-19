package com.base;

import com.base.validators.LoginValidator;

public record LoginUser (String name, String password) implements LoginValidator {
   public LoginUser(String name, String password){
      this.name=name;
      this.password=password;
   }
} ;


