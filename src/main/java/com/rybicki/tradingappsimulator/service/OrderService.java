package com.rybicki.tradingappsimulator.service;

import com.rybicki.tradingappsimulator.model.DowJones30Company;
import com.rybicki.tradingappsimulator.model.Money;
import com.rybicki.tradingappsimulator.model.Purchase;
import com.rybicki.tradingappsimulator.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class OrderService {

    //TODO change it into bean
    RestTemplate restTemplate = new RestTemplate();

    public void buyStocks(User user, DowJones30Company dowJones30Company, Money money) {
        //TODO restTemplate
        user.getWallet().add(new Purchase(UUID.randomUUID().toString(), dowJones30Company, money));
        System.out.println("Company " + dowJones30Company.getIndex() + " added to wallet for user " + user.getId() + " for " + money.getAmount() + money.getCurrency());
    }

    //Users always sell all stocks
    public void sellStocks(User user, DowJones30Company dowJones30Company) {
        //TODO restTemplate
        user.getWallet().remove(dowJones30Company);
        System.out.println("Company " + dowJones30Company.getIndex() + " removed from wallet for user " + user.getId());
    }
}
