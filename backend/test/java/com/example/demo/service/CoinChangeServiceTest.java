package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoinChangeServiceTest {

    private final CoinChangeService coinChangeService = new CoinChangeService();

    @Test
    public void testGetMinimumCoins_basicCase() {
        double target = 7.0;
        List<Double> denominations = Arrays.asList(1.0, 2.0, 5.0);

        List<Double> result = coinChangeService.getMinimumCoins(target, denominations);

        assertNotNull(result);
        assertEquals(7.0, result.stream().mapToDouble(Double::doubleValue).sum(), 0.001);
    }

    @Test
    public void testGetMinimumCoins_noSolution() {
        double target = 3.0;
        List<Double> denominations = Arrays.asList(2.0, 4.0);

        List<Double> result = coinChangeService.getMinimumCoins(target, denominations);

        assertTrue(result.isEmpty());
    }
}
