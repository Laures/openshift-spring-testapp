package com.openshift.test.service;

import javax.inject.Named;

import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
public class EchoService {

    public String test(String message) {
        log.info("received message '{}' and will echo it", message);
        return message;
    }
}
