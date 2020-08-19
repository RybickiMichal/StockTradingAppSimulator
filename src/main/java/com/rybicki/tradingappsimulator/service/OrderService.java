package com.rybicki.tradingappsimulator.service;

import com.rybicki.tradingappsimulator.model.DowJones30Company;
import com.rybicki.tradingappsimulator.model.Money;
import com.rybicki.tradingappsimulator.model.Purchase;
import com.rybicki.tradingappsimulator.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static com.rybicki.tradingappsimulator.model.OrderType.BUY;
import static com.rybicki.tradingappsimulator.model.OrderType.SELL;

@Service
@Slf4j
public class OrderService {

    //TODO change it into bean
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String resourceUrl = "http://localhost:8081/order/create";

    public void buyStocks(User user, DowJones30Company dowJones30Company, Money money) {
        ResponseEntity<String> response = restTemplate.getForEntity(buildUrl(BUY.getType(), user.getId(),
                dowJones30Company.getIndex(), money.getCurrency(), money.getAmount().toString()), String.class);

        user.getWallet().put(dowJones30Company.getIndex(), new Purchase(UUID.randomUUID().toString(), dowJones30Company, money));
        log.info(response + " for Company " + dowJones30Company.getIndex() + " added to wallet for user " +
                user.getId() + " for " + money.getAmount() + " " + money.getCurrency());
    }

    //Users always sell all stocks of given company
    public void sellStocks(User user, DowJones30Company dowJones30Company) {
        ResponseEntity<String> response = restTemplate.getForEntity(buildUrl(SELL.getType(), user.getId(),
                dowJones30Company.getIndex(), null, null), String.class);

        user.getWallet().remove(dowJones30Company.getIndex());
        log.info(response + " for Company " + dowJones30Company.getIndex() + " removed from wallet for user " + user.getId());
    }

    private String buildUrl(String orderType, String userId, String index, String currency, String amount) {
        validateOrderType(orderType);

        if (orderType.equals(BUY.getType())) {
            return resourceUrl + "/" + orderType + "/" + userId + "/" + index + "/" + currency + "/" + amount;
        } else {
            return resourceUrl + "/" + orderType + "/" + userId + "/" + index;
        }
    }

    private void validateOrderType(String orderType) {
        if (isWrongOrderType(orderType)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isWrongOrderType(String orderType) {
        if (orderType.equals(SELL.getType()) || orderType.equals(BUY.getType())) {
            return false;
        }
        return true;
    }
}
