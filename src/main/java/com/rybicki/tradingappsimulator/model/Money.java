package com.rybicki.tradingappsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class Money {
    private String currency;
    private BigDecimal amount;
}
