package com.rybicki.tradingappsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Purchase {
    @Id
    private final String id;
    private DowJones30Company dowJones30Company;
    private Money money;
}
