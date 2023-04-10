package com.becom.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/send/sendMessage")
    public String sendMessage(){
      return "hello message";
    }
    @GetMapping("/getuserinfo")
    public String getuserinfo(){
        return "hello authmessage";
    }
}
