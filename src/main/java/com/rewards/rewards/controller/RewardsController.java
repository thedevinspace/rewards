package com.rewards.rewards.controller;

import com.rewards.rewards.model.Rewards;
import com.rewards.rewards.services.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/rewards/getRewardsPerMonth")
    @ResponseBody
    public Stream<Rewards> getRewards() {
        return this.rewardsService.getRewards();
    }
}
