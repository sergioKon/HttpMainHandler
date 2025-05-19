package com.base;

import com.base.validators.LoginValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record LoginUser (String name, String password) implements LoginValidator {
   static Logger logger = LogManager.getLogger(LoginUser.class);

    public LoginUser {
        logger.debug(" username =  {} password {}", name, "*********");
    }
}


