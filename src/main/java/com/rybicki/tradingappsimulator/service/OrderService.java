package com.rybicki.tradingappsimulator.service;

import com.rybicki.tradingappsimulator.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class OrderService {

    private RestTemplate restTemplate;

    private static final String resourceUrl = "http://localhost:8081/order/create";

    public void buyStocks(OrderDTO orderDTO, User user) {
        ResponseEntity<String> response = restTemplate.postForEntity(resourceUrl, orderDTO, String.class);

        user.getWallet().put(orderDTO.getCompanyIndex(), new Purchase(UUID.randomUUID().toString(),
                DowJones30Company.getValue(orderDTO.getCompanyIndex()), new Money(orderDTO.getMoney().getCurrency(), orderDTO.getMoney().getAmount())));

        log.info(response + " for Company " + orderDTO.getCompanyIndex() + " added to wallet for user " +
                user.getId() + " for " + orderDTO.getMoney().getAmount() + " " + orderDTO.getMoney().getCurrency());
    }

    //Users always sell all stocks of given company
    public void sellStocks(OrderDTO orderDTO, User user) {
        ResponseEntity<String> response = restTemplate.postForEntity(resourceUrl, orderDTO, String.class);

        user.getWallet().remove(orderDTO.getCompanyIndex());

        log.info(response + " for Company " + orderDTO.getCompanyIndex() + " removed from wallet for user " + user.getId());
    }
}


