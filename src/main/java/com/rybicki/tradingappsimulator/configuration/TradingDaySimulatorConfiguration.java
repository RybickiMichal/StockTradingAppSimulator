package com.rybicki.tradingappsimulator.configuration;

import com.rybicki.tradingappsimulator.service.StockCalendarService;
import com.rybicki.tradingappsimulator.service.TradingDaySimulatorService;
import com.rybicki.tradingappsimulator.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@AllArgsConstructor
@Configuration
@EnableAsync
@EnableScheduling
@Slf4j
public class TradingDaySimulatorConfiguration {

    private final UserService userService;
    private final TradingDaySimulatorService tradingDaySimulatorService;
    private final StockCalendarService stockCalendarService;

    @Async
    @Scheduled(fixedRate = 1000)
    public void simulateTradingDay() {
        stockCalendarService.initNewDayInStockMarket();

        userService.getUsers()
                .forEach(user -> tradingDaySimulatorService.simulateDayForUser(user));

        log.info("Trading day has been simulated " + System.currentTimeMillis() / 1000);
    }
}
