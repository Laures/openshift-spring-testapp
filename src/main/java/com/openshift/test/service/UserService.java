package com.openshift.test.service;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Delegate;
import lombok.Setter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.openshift.test.dao.UserDao;

@Named("UserService")
public class UserService implements UserDetailsService {

    @Inject
    @Setter
    @Delegate
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findOne(Long.parseLong(username));
    }

}
