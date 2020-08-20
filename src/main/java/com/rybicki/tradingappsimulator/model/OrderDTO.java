package com.rybicki.tradingappsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class OrderDTO {
    private OrderType orderType;
    private String userId;
    private String companyIndex;
    private Money money;
}
