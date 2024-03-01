package com.example.SubscribedBilling.dao;

import com.example.SubscribedBilling.dto.productDTO;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

@Mapper
public interface productDAO {
    void setProduct(productDTO productDTO);

    List<Map<String, Object>> getProductList();

    void joinSubscription(JSONObject param);

    void cancelSubscription(JSONObject param);

    void setBilling_history(JSONObject param);

    void setCancelBilling_history(JSONObject param);
}
