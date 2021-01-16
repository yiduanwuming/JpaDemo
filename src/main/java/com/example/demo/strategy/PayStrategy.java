package com.example.demo.strategy;

public interface PayStrategy {
    boolean pay(int price);
    void collectPaymentDetails();
}
