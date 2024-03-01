package com.example.SubscribedBilling.service;

import com.example.SubscribedBilling.dao.billingDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class billingSchedulerService {

    @Autowired
    billingDAO billingDAO;



    @Scheduled (cron = "0 1 * * *")
    public void daliy_blilling(){

        try{

            billingDAO.daliy_blilling();
            log.info("daliy_blilling_SUCCESS");

        }catch (Exception e){
            log.error("Exception = {}",e);


        }


    }
}
