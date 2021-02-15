package com.juanmougan.examples.graphql.subscriptions.graphql;

import com.juanmougan.examples.graphql.subscriptions.model.Crypto;
import com.juanmougan.examples.graphql.subscriptions.model.Price;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.math.BigDecimal;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriceQuery implements GraphQLQueryResolver {

  public Price price(Crypto crypto) {
    log.info("Getting price for: {}", crypto);
    Random random = new Random();
    return Price.builder()
        .type(crypto)
        .price(new BigDecimal(random.nextInt(45000 - 40000) + 40000))
        .build();
  }
}
