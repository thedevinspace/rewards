package com.rewards.rewards.model;

import lombok.Data;

import java.util.Map;

@Data
public class Rewards {
    int customerId;
    Map<String, Integer> rewardsPerMonth;
    int rewardsTotal;
}
