package com.rybicki.tradingappsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class Purchase {
    @Id
    private final String id;
    private DowJones30Company dowJones30Company;
    private Money money;
}
