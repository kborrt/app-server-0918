package com.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
@Controller
public class BaseController {
    @Autowired
     public HttpServletRequest request;
}
