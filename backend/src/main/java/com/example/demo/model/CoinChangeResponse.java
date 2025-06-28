package com.example.demo.model;

import java.util.List;

public class CoinChangeResponse {
    private List<Double> coins;

    public CoinChangeResponse(List<Double> coins) {
        this.coins = coins;
    }

    public List<Double> getCoins() {
        return coins;
    }

    public void setCoins(List<Double> coins) {
        this.coins = coins;
    }
}
