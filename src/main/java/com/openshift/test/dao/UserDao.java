package com.openshift.test.dao;

import org.springframework.data.repository.CrudRepository;

import com.openshift.test.model.User;

public interface UserDao extends CrudRepository<User, Long> {

}
