package com.rybicki.tradingappsimulator.model;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum UserStrategy {
    LONGTERM,
    SHORTTERM,
    HYBRID;

    private static final List<UserStrategy> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final SecureRandom RANDOM = new SecureRandom();

    public static UserStrategy getRandomUserStrategy() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }
}
