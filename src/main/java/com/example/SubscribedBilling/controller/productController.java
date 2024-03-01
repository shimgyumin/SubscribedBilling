package com.example.SubscribedBilling.controller;

import com.example.SubscribedBilling.dto.productDTO;
import com.example.SubscribedBilling.service.productService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    productService productService;


//관리자 상품등록
    @PostMapping("/setProduct")
    public JSONObject setProduct(HttpServletRequest req,
                                 productDTO productDTO){

        JSONObject result = new JSONObject();

       result = productService.setProduct(req,productDTO);

        return result;


    }

    //상품리스트
    @GetMapping("/getProductList")
    public JSONObject getProductList(HttpServletRequest req){

        JSONObject result = new JSONObject();

        result = productService.getProductList();

        return result;

    }

    @PostMapping("/joinSubscription")
    public JSONObject joinSubscription (HttpServletRequest req,
                                        @RequestParam("productIdx") int productIdx
                                        ){

        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();
        param.put("productIdx", productIdx);
        param.put("userID", req.getHeader("userID"));

        result = productService.joinSubscription(param);

        return result;
    }


    @PostMapping("/cancelSubscription")
    public JSONObject cancelSubscription (HttpServletRequest req,
                                          @RequestParam("productIdx") int productIdx){


        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();

        param.put("productIdx", productIdx);
        param.put("userID", req.getHeader("userID"));

        result = productService.cancelSubscription(param);

        return result;

    }

}
