package com.example.SubscribedBilling.controller;

import com.example.SubscribedBilling.dto.userDTO;
import com.example.SubscribedBilling.service.userService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping(value ="/user")
public class userController {

    @Autowired
    userService userService;

    //회원가입
       @PostMapping(value = "/createUser")
    public JSONObject createUser(HttpServletRequest req, userDTO userDTO) {

        JSONObject result = new JSONObject();

           result = userService.createUser(userDTO);

        return result;
    }


    // 로그인
    @PostMapping(value = "/login")
    public JSONObject userLogin(HttpServletRequest req,
                                userDTO userDTO){

        JSONObject result = new JSONObject();
        result=userService.userLogin(userDTO);

        if(result.get("status").equals("0000")){

            String accessToken = userService.getToken(userDTO);
            result.put("accessToken", accessToken);

        }

        return result;
    }

}
