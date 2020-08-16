package com.rybicki.tradingappsimulator.reposiory;

import com.rybicki.tradingappsimulator.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {}
