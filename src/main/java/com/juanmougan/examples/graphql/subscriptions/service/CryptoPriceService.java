package com.juanmougan.examples.graphql.subscriptions.service;

import com.juanmougan.examples.graphql.subscriptions.model.Crypto;
import com.juanmougan.examples.graphql.subscriptions.model.Price;
import java.math.BigDecimal;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class CryptoPriceService {

  public Price getCurrentPrice(Crypto crypto) {
    Random random = new Random();
    return Price.builder()
        .type(crypto)
        .price(new BigDecimal(random.nextInt(45000 - 40000) + 40000))
        .build();
  }
}
