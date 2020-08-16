package com.rybicki.tradingappsimulator.configuration;

import com.rybicki.tradingappsimulator.service.StockCalendarService;
import com.rybicki.tradingappsimulator.service.TradingDaySimulatorService;
import com.rybicki.tradingappsimulator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
@EnableAsync
public class TradingDaySimulatorConfig {

    private final UserService userService;
    private final TradingDaySimulatorService tradingDaySimulatorService;
    private final StockCalendarService stockCalendarService;

    @Async
    @Scheduled(fixedRate  = 1000)
    public void simulateTradingDay() {
        stockCalendarService.initNewDayInStockMarket();

        userService.getUsers()
                .forEach(user -> tradingDaySimulatorService.simulateDayForUser(user));

        System.out.println("Trading day has been simulated " + System.currentTimeMillis() / 1000);
    }
}
