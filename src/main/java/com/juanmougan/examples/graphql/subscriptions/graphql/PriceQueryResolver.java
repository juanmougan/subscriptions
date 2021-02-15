package com.juanmougan.examples.graphql.subscriptions.graphql;

import com.juanmougan.examples.graphql.subscriptions.model.Crypto;
import com.juanmougan.examples.graphql.subscriptions.model.Price;
import com.juanmougan.examples.graphql.subscriptions.service.CryptoPriceService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PriceQueryResolver implements GraphQLQueryResolver {

  private final CryptoPriceService cryptoPriceService;

  public Price price(Crypto crypto) {
    log.info("Getting price for: {}", crypto);
    return cryptoPriceService.getCurrentPrice(crypto);
  }
}
