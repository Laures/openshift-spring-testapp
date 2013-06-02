package com.openshift.test.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Slf4j
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object salt) throws DataAccessException {
        log.debug("Encoding password.");
        return BCrypt.hashpw(rawPass, BCrypt.gensalt());
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
        log.debug("Validating password.");
        return BCrypt.checkpw(rawPass, encPass);
    }
}