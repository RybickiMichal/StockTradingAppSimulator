package com.rybicki.tradingappsimulator.service;

import com.rybicki.tradingappsimulator.model.User;
import com.rybicki.tradingappsimulator.model.UserStrategy;
import com.rybicki.tradingappsimulator.reposiory.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void initUsers(int numberOfUsers) {
        SecureRandom random = new SecureRandom();

        userRepository.deleteAll();

        for (int x = 0; x < numberOfUsers; x++) {
            userRepository.insert(User.builder()
                    .accountBalance(new BigDecimal(0))
                    .everyMonthPayment(random.nextInt(100000))
                    .id(Integer.toString(x))
                    .userStrategy(UserStrategy.getRandomUserStrategy())
                    .wallet(new HashMap<>())
                    .build());
        }
        log.info("actual users number = " + userRepository.count());
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void actualiseUser(User user) {
        userRepository.save(user);
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }
}
