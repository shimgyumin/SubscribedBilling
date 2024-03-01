package com.example.SubscribedBilling.service;

import com.example.SubscribedBilling.common.jwtProvider;
import com.example.SubscribedBilling.dao.userDAO;
import com.example.SubscribedBilling.dto.userDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Component
public class userService {

    @Autowired
    userDAO userDAO;

    @Autowired
    jwtProvider jwtProvider;

    @Value("${jwt.expTime}")
    private long min;
    @Value("${jwt.expTime.refresh}")
    private long refreshMin;

    public JSONObject createUser(userDTO userDTO) {

        JSONObject result = new JSONObject();

        try {

            if(userDAO.userIDChk(userDTO)>0) {

                result.put("status", "0002");
                log.info("== 중복 된 아이디가 존재합니다. ==");
                return result;
            }

            userDAO.createUser(userDTO);
            result.put("status", "0000");
            log.info("== User Create Success ==");

        } catch (Exception e) {

            result.put("status", "0001");
            log.info("LOGIN Exception = {}", e);

        }

        return result;
    }

    public String getToken(userDTO userDTO) {

        String accessToken = null;

        try {
            String UserID = userDTO.getUserID();
            String UserName = userDTO.getUserName();
            int UserLevel = userDTO.getUserLevel();
            String Token = jwtProvider.createToken(UserID, UserName, UserLevel, (min * 1000 * 60), "access");
            String refreshToken = jwtProvider.createToken(UserID, UserName, UserLevel, (refreshMin * 1000 * 60), "reToken");
            insertRetoken(UserID, refreshToken);
            accessToken = Token;


        } catch (Exception e) {

            log.info("Exception = {}",e);

        }

        return accessToken;

    }

    private void insertRetoken(String userID, String refreshToken) {

        try {

            userDAO.insertRetoken(userID,refreshToken);

        }catch (Exception e ){

            log.info("Exception = {}" , e);

        }
    }

    public JSONObject userLogin(userDTO userDTO) {

        JSONObject result = new JSONObject();

        try {

            if(userDAO.userIDChk(userDTO)<0){

                result.put("status","0002");
                log.info("아이디를 확인해주세요.");
                return result;

            }else if (userDAO.passwordChk(userDTO)<0){

                result.put("status","0003");
                log.info("비밀번호를 확인해주세요.");
                return result;
            }
            result.put("status","0000");
            log.info("로그인 성공");

        }catch (Exception e){

            result.put("status","0001");
            log.info("Exception = {}" , e);

        }


        return result;
    }
}
