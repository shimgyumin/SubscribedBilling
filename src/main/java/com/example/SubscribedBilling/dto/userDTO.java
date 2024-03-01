package com.example.SubscribedBilling.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class userDTO {
    String userID;
     String userName;
     String password;
     String email;
     int userLevel;

    public userDTO() {
    }

}
