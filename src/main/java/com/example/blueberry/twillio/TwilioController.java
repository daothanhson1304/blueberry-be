package com.example.blueberry.twillio;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/api/twillio")
public class TwilioController {

    @Autowired
    PhoneverificationService phonesmsservice;

//    @RequestMapping("/")
//    public String homepage(ModelAndView model)
//    {
////        return "index";
//    }

    @PostMapping("/sendotp/{phone}")
    public ResponseEntity<String> sendotp(@PathVariable("phone") String phone)
    {
        VerificationResult result=phonesmsservice.startVerification(phone);
        System.out.println(phone);
        if(result.isValid())
        {
            return new ResponseEntity<>("Otp Sent..",HttpStatus.OK);
        }
        return new ResponseEntity<>("Otp failed to sent..",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/verifyotp")
    public ResponseEntity<String> sendotp(@RequestParam("phone") String phone, @RequestParam("otp") String otp)
    {
        VerificationResult result=phonesmsservice.checkverification(phone,otp);
        if(result.isValid())
        {
            return new ResponseEntity<>("Your number is Verified",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something wrong/ Otp incorrect",HttpStatus.BAD_REQUEST);
    }


}