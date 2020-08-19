package com.rybicki.tradingappsimulator.service;

import com.rybicki.tradingappsimulator.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Slf4j
public class StockCalendarService {

    public StockCalendarService(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
    private final Calendar calendar = new GregorianCalendar(2020, Calendar.JANUARY, 1);

    //users always send money in every second day of the month
    public void initNewDayInStockMarket(){
        setNextDayInCalendar();
        if(calendar.get(Calendar.DAY_OF_MONTH) == 2){
            addMoneyForUsers();
        }
    }

    private void setNextDayInCalendar() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        log.info("today is " + calendar.getTime().toString());
    }

    public void addMoneyForUsers() {
        for (User user : userService.getUsers()) {
            user.setAccountBalance(new BigDecimal(user.getAccountBalance().doubleValue()).add(new BigDecimal(user.getEveryMonthPayment())));
            userService.actualiseUser(user);
            log.info("new User Account Ballance equal " + user.getAccountBalance().intValue());
        }
    }
}
