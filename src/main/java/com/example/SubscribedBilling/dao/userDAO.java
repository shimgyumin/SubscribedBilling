package com.example.SubscribedBilling.dao;


import com.example.SubscribedBilling.dto.userDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userDAO {
    void createUser(userDTO userDTO);

    void insertRetoken(String userID, String refreshToken);

    int userIDChk(userDTO userDTO);

    int passwordChk(userDTO userDTO);
}
