package com.BrennoDS.cadastro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    

    public String test() {
        return "Test mapping";
    }

}
