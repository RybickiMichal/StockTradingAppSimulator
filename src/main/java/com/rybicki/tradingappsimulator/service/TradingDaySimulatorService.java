package com.rybicki.tradingappsimulator.service;

import com.rybicki.tradingappsimulator.model.DowJones30Company;
import com.rybicki.tradingappsimulator.model.Money;
import com.rybicki.tradingappsimulator.model.Purchase;
import com.rybicki.tradingappsimulator.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Map;

import static com.rybicki.tradingappsimulator.model.UserStrategy.*;

@Service
@AllArgsConstructor
public class TradingDaySimulatorService {

    private final OrderService orderService;
    private final UserService userService;

    public void simulateDayForUser(User user) {
        SecureRandom random = new SecureRandom();
        if (user.getUserStrategy().equals(LONGTERM)) {
            simulateDayForLongTermUser(random, user);
        } else if (user.getUserStrategy().equals(SHORTTERM)) {
            simulateDayForShortTermUser(random, user);
        } else if (user.getUserStrategy().equals(HYBRID)) {
            simulateDayForUserWithHybridStrategy(random, user);
        }
    }

    //long term users buy/sell stocks about ones per month
    private void simulateDayForLongTermUser(SecureRandom random, User user) {
        if (random.nextInt(30) == 1) {
            buyOrSellStocks(random, user);
        }
    }

    //short term users buy/sell stocks everyday
    private void simulateDayForShortTermUser(SecureRandom random, User user) {
        buyOrSellStocks(random, user);
    }

    //users with hybrid strategy buy/sell stocks about six times per month
    private void simulateDayForUserWithHybridStrategy(SecureRandom random, User user) {
        if (random.nextInt(5) == 1) {
            buyOrSellStocks(random, user);
        }
    }

    //possibility to buy is twice higher than to sell stocks
    private void buyOrSellStocks(SecureRandom random, User user) {
        if (random.nextInt(3) == 1) {
            sellRandomUserStocks(random, user);
        } else {
            buyRandomStocks(user);
        }
    }

    private void sellRandomUserStocks(SecureRandom random, User user) {
        if (CollectionUtils.isEmpty(user.getWallet())) {
            System.out.println("wallet is empty for user with id " + user.getId());
        } else {
            orderService.sellStocks(user, getRandomCompanyFromWallet(random, user.getWallet()));

            //TODO change hardcoded value to real stock value
            user.setAccountBalance(user.getAccountBalance().add(new BigDecimal(20000)));
            userService.actualiseUser(user);
        }
    }

    //users always buy stocks for half of they available money
    private void buyRandomStocks(User user) {
        if (user.getAccountBalance().doubleValue() == 0) {
            System.out.println("Account balance equal 0 for user id " + user.getId());
        } else {
            BigDecimal halfOfAccountBalance = user.getAccountBalance().divide(new BigDecimal(2));
            orderService.buyStocks(user, DowJones30Company.getRandomDowJones30Company(), new Money("USD", halfOfAccountBalance));

            user.setAccountBalance(user.getAccountBalance().divide(new BigDecimal(2)));
            userService.actualiseUser(user);
        }
    }

    private DowJones30Company getRandomCompanyFromWallet(SecureRandom random, Map<String, Purchase> wallet) {
        String[] purchases = wallet.keySet().toArray(new String[0]);
        return wallet.get(purchases[random.nextInt(wallet.size())]).getDowJones30Company();
    }

}
