package com.example.SubscribedBilling.common;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class jwtProvider {

    public String secretKey;

    public String refreshSecretKey;

    @Value("${jwt.secret}")
    public void setSecretKey(String val) {
        secretKey = val;
    }

    @Value("${jwt.secret.refresh}")
    public void setRefreshSecretKeyKey(String val) {
        refreshSecretKey = val;
    }


    public String createToken(String userID, String userName, int userLevel, long expTime, String tokenLevel) {
        String secretKey = "";


       if(expTime == 0){
           throw new RuntimeException("유효하지 않는 토큰 입니다.");
       }
       if(tokenLevel.equals("access")){

           secretKey = this.secretKey;

       } else if (tokenLevel.equals("reToken")){

           secretKey = this.refreshSecretKey;
       } else {
           throw new RuntimeException("Specify token Levle");
       }


        Claims claims = Jwts.claims().setSubject(userID);
        claims.put("userID", userID);
        claims.put("userName", userName);
        claims.put("userLevel", userLevel);

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        System.out.println("00000 ==="+secretKey);
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userID)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }
}
