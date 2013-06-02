package com.openshift.test.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openshift.test.dao.UserDao;

@Controller
@RequestMapping("/status")
public class ApplicationStatusController {

    @Inject
    private UserDao userDao;

    @Value("${OPENSHIFT_APP_NAME}")
    private String appName;

    @Value("${OPENSHIFT_APP_DNS}")
    private String appDns;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET})
    public Map<String, String> getStatus() {
        Map<String, String> result = new HashMap<>();

        result.put("application_name", appName);
        result.put("application_url", appDns);
        result.put("users_count", userDao.count() + "");

        return result;
    }

}
