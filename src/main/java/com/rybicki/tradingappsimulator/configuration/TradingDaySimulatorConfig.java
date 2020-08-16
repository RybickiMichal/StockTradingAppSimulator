package com.rybicki.tradingappsimulator.configuration;

import com.rybicki.tradingappsimulator.service.TradingDaySimulatorService;
import com.rybicki.tradingappsimulator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class TradingDaySimulatorConfig {

    private UserService userService;
    private TradingDaySimulatorService tradingDaySimulatorService;

    @Scheduled(fixedDelay = 1000)
    private void simulateTradingDay() {
        userService.getUsers()
                .forEach(user -> tradingDaySimulatorService.simulateDayForUser(user));

        System.out.println("Trading day has been simulated " + System.currentTimeMillis() / 1000);
    }
}
