package com.rybicki.tradingappsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public enum DowJones30Company {
    MMM("MMM"),
    AMERICANEXPRESS("AXP"),
    APPLE("AAPL"),
    BOEING("BA"),
    CATERPILLAR("CAT"),
    CHEVREON("CVX"),
    CISCO("CSCO"),
    COCACOLA("KO"),
    DOWINC("DOW"),
    EXXONMOBILE("XOM"),
    GOLDMANSACHS("GS"),
    HOMEDEPOT("HD"),
    IBM("IBM"),
    INTEL("INTC"),
    JOHNSONANDJOHNSON("JNJ"),
    JPMORGAN("JPM"),
    MCDONALDS("MCD"),
    MERCKANDCO("MRK"),
    MICROSOFT("MSFT"),
    NIKE("NKE"),
    PFIZER("PFE"),
    PROCTERANDGAMBLE("PG"),
    RAYTHEON("RTX"),
    TRAVELERSCOMPANIES("TRV"),
    UNITEDHEALTH("UNH"),
    VERIZON("VZ"),
    VISA("V"),
    WALMART("WMT"),
    WALGREENSBOOTS("WBA"),
    WALTDISNEY("DIS");

    @Getter
    private final String index;

    private static final List<DowJones30Company> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final SecureRandom RANDOM = new SecureRandom();

    public static DowJones30Company getRandomDowJones30Company() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }
}
