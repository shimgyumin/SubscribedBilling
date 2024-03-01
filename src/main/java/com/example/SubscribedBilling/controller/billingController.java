package com.example.SubscribedBilling.controller;


import com.example.SubscribedBilling.service.billingService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/billing")
public class billingController {

    @Autowired
    billingService billingService;

    @GetMapping("/getBillingList")
        public JSONObject getBillingList(HttpServletRequest req,
                                         @RequestParam("billingType") int billingType){

        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("userID", req.getHeader("userID"));
        param.put("billingType", billingType);

        result = billingService.getBillingList(param);


        return result;

        }




}
