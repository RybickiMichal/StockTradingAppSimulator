package com.rybicki.tradingappsimulator.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document
@Builder
public class User {
    @Id
    private final String id;
    private BigDecimal accountBalance;
    private UserStrategy userStrategy;
    private int everyMonthPayment;
    private List<Purchase> wallet;
}
