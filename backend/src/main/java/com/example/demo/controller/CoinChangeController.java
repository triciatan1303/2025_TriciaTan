package com.example.demo.controller;

import com.example.demo.model.CoinChangeRequest;
import com.example.demo.model.CoinChangeResponse;
import com.example.demo.service.CoinChangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://20.123.88.146:3000")
public class CoinChangeController {

    @Autowired
    private CoinChangeService coinChangeService;

    @PostMapping("/coin-change")
    public CoinChangeResponse getCoinChange(@RequestBody CoinChangeRequest request) {
        var coins = coinChangeService.getMinimumCoins(request.getTargetAmount(), request.getDenominations());
        return new CoinChangeResponse(coins);
    }
}
