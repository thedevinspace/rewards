package com.rewards.rewards.services;

import com.rewards.rewards.dbutils.TransactionRepository;
import com.rewards.rewards.model.Rewards;
import com.rewards.rewards.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RewardsService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Stream<Rewards> getRewards() {
        return transactionRepository.getAllTransactions().stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId,
                        Collectors.groupingBy(Transaction::getTransactionMonth)))
                .entrySet()
                .stream()
                .map(groupByCustomerAndMonth -> {
                    Rewards rewards = new Rewards();
                    rewards.setCustomerId(groupByCustomerAndMonth.getKey());
                    Map<String, Integer> rewardsPerMonth = new HashMap<>();
                    AtomicInteger totalRewardPoints = new AtomicInteger();
                    groupByCustomerAndMonth.getValue()
                            .forEach((groupByMonthKey, groupByMonthValue) -> {
                                int monthlyRewards = groupByMonthValue
                                        .stream()
                                        .map(Transaction::getRewards)
                                        .reduce(0, Integer::sum);
                                totalRewardPoints.set(totalRewardPoints.get() + monthlyRewards);
                                rewardsPerMonth.put(groupByMonthKey, monthlyRewards);
                            });
                    rewards.setRewardsPerMonth(rewardsPerMonth);
                    rewards.setRewardsTotal(totalRewardPoints.get());
                    return rewards;
                });

    }

}