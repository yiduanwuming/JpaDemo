package com.example.demo.strategy;

import lombok.Data;

@Data
public class Order {
    private int totalCost = 0;
    private boolean isClosed;

    public void processOrder(PayStrategy payStrategy) {
        payStrategy.collectPaymentDetails();
    }
}
