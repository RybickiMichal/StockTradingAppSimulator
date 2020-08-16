package com.rybicki.tradingappsimulator.service;

import com.rybicki.tradingappsimulator.model.DowJones30Company;
import com.rybicki.tradingappsimulator.model.Money;
import com.rybicki.tradingappsimulator.model.Purchase;
import com.rybicki.tradingappsimulator.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    //TODO change it into bean
    private final RestTemplate restTemplate = new RestTemplate();

    public void buyStocks(User user, DowJones30Company dowJones30Company, Money money) {
        //TODO restTemplate
        user.getWallet().put(dowJones30Company.getIndex(), new Purchase(UUID.randomUUID().toString(), dowJones30Company, money));
        log.info("Company " + dowJones30Company.getIndex() + " added to wallet for user " + user.getId() + " for " + money.getAmount() + " " + money.getCurrency());
    }

    //Users always sell all stocks
    public void sellStocks(User user, DowJones30Company dowJones30Company) {
        //TODO restTemplate
        user.getWallet().remove(dowJones30Company.getIndex());
        log.info("Company " + dowJones30Company.getIndex() + " removed from wallet for user " + user.getId());
    }
}
