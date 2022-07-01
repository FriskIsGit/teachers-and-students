package com.base.teachersstudents.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIErrorController implements ErrorController{
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorOccurred() {
        System.out.println("Error occurred");
        return "There's nothing here";
    }
}
