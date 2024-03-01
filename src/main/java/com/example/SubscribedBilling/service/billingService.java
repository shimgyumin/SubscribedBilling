package com.example.SubscribedBilling.service;


import com.example.SubscribedBilling.dao.billingDAO;
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
public class billingService {
    @Autowired
    billingDAO billingDAO;


    public JSONObject getBillingList(JSONObject param) {

        JSONObject result = new JSONObject();
        List<Map<String, Object>> billingList = new ArrayList<>();

        int billingType = (int) param.get("billingType");

        try {

            switch (billingType){

                //일일 리스트
                case 1 :
                    billingList =billingDAO.getDailyBillingList();
                    result.put("output", billingList);
                    break;

                //월별 리스트
                case 2:
                    billingList =billingDAO.getMonthBillingList();
                    result.put("output", billingList);
                    break;

                //연도별 리스트
                case 3:
                    billingList =billingDAO.getYearBillingList();
                    result.put("output", billingList);
                    break;

            }

            result.put("status", "0000");

        }catch (Exception e){

            log.error("Exception = {}",e);
            result.put("status", "0001");

        }

        return result;
    }
}
