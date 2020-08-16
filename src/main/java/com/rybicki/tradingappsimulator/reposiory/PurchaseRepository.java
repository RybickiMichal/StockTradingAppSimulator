package com.rybicki.tradingappsimulator.reposiory;

import com.rybicki.tradingappsimulator.model.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, String> {}
