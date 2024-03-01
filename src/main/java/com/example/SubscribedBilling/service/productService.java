package com.example.SubscribedBilling.service;

import com.example.SubscribedBilling.dao.productDAO;
import com.example.SubscribedBilling.dto.productDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Component
public class productService {

    @Autowired
    productDAO productDAO;

    public JSONObject setProduct(HttpServletRequest req, productDTO productDTO) {

        JSONObject result = new JSONObject();

        try {

            productDAO.setProduct(productDTO);
            result.put("status", "0000");


        }catch (Exception e){
            log.error("Exception = {}",e);
            result.put("status", "0001");

        }
        return result;
    }

    public JSONObject getProductList() {

        JSONObject result = new JSONObject();
        List<Map<String, Object>> ProductList = new ArrayList<>();

        try{

            ProductList =productDAO.getProductList();
            result.put("output", ProductList);
            result.put("status", "0000");



        }catch (Exception e){

            log.error("Exception = {}",e);
            result.put("status", "0001");

        }

        return result;

    }

    public JSONObject joinSubscription(JSONObject param) {

        JSONObject result = new JSONObject();

        try {

            productDAO.joinSubscription(param);
            productDAO.setBilling_history(param);
            result.put("status", "0000");

        }catch (Exception e){
            log.error("Exception = {}",e);
            result.put("status", "0001");


        }

        return result;
    }

    public JSONObject cancelSubscription(JSONObject param) {

        JSONObject result = new JSONObject();

        try {

            productDAO.cancelSubscription(param);
            productDAO.setCancelBilling_history(param);
            result.put("status", "0000");

        }catch (Exception e){
            log.error("Exception = {}",e);
            result.put("status", "0001");

        }
        return result;
    }
}
