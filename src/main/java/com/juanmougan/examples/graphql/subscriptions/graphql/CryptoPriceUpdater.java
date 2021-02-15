package com.juanmougan.examples.graphql.subscriptions.graphql;

import com.juanmougan.examples.graphql.subscriptions.model.Crypto;
import com.juanmougan.examples.graphql.subscriptions.model.PriceUpdate;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class CryptoPriceUpdater implements GraphQLSubscriptionResolver {

  // This name has to match the one defined in the schema
  public Publisher<PriceUpdate> cryptoPrice(Crypto crypto) {
    log.info("Updating price for: {}", crypto);
    Random random = new Random();
    return Flux.interval(Duration.ofSeconds(10))
        .map(val -> PriceUpdate.builder()
            .timestamp(LocalDateTime.now())
            .type(crypto)
            .price(new BigDecimal(random.nextInt(45000 - 40000) + 40000))
            .build());
  }
}
