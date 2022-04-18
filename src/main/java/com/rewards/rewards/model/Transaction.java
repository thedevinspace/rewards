package com.rewards.rewards.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Transaction {
    int transactionId;
    String transactionMonth;
    int price;
    int customerId;
    int rewards;

    public Transaction(int transactionId, String transactionMonth, int price, int customerId){
        this.transactionMonth = transactionMonth;
        this.transactionId = transactionId;
        this.price = price;
        this.customerId = customerId;
        this.rewards = this.calculateRewards(price);
    }

    private int calculateRewards(int price) {
        if (price >= 50 && price < 100) {
            return price - 50;
        } else if (price > 100) {
            return (2 * (price - 100) + 50);
        }
        return 0;
    }
}