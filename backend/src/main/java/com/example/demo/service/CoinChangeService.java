package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class CoinChangeService {

    public List<Double> getMinimumCoins(double targetAmount, List<Double> denominations) {
        int target = (int) Math.round(targetAmount * 100);
        List<Integer> coinValues = denominations.stream()
                .map(d -> (int) Math.round(d * 100))
                .sorted()
                .toList();

        int[] dp = new int[target + 1];
        int[] prev = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= target; i++) {
            for (int coin : coinValues) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    if (dp[i] > dp[i - coin] + 1) {
                        dp[i] = dp[i - coin] + 1;
                        prev[i] = coin;
                    }
                }
            }
        }

        if (dp[target] == Integer.MAX_VALUE) {
            return new ArrayList<>();
        }

        List<Double> result = new ArrayList<>();
        while (target > 0) {
            int coin = prev[target];
            result.add(coin / 100.0);
            target -= coin;
        }

        result.sort(Comparator.naturalOrder());
        return result;
    }
}
