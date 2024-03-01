package com.example.SubscribedBilling.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface billingDAO {
    void daliy_blilling();


    List<Map<String, Object>> getDailyBillingList();

    List<Map<String, Object>> getMonthBillingList();

    List<Map<String, Object>> getYearBillingList();
}
