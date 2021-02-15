package com.juanmougan.examples.graphql.subscriptions.graphql;

import com.juanmougan.examples.graphql.subscriptions.model.Crypto;
import com.juanmougan.examples.graphql.subscriptions.model.Price;
import com.juanmougan.examples.graphql.subscriptions.model.PriceUpdate;
import com.juanmougan.examples.graphql.subscriptions.service.CryptoPriceService;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CryptoPriceUpdater implements GraphQLSubscriptionResolver {

  private static final ModelMapper MAPPER = new ModelMapper();
  private final CryptoPriceService cryptoPriceService;

  private static PriceUpdate fromPrice(Price price) {
    return MAPPER.map(price, PriceUpdate.class);
  }

  @PostConstruct
  private void postConstruct() {
    MAPPER.createTypeMap(Price.class, PriceUpdate.class).setProvider(
        provisionRequest -> {
          Price p = (Price) provisionRequest.getSource();
          return PriceUpdate.builder()
              .price(p.getPrice())
              .type(p.getType())
              .timestamp(LocalDateTime.now())
              .build();
        });
  }

  // This name has to match the one defined in the schema
  public Publisher<PriceUpdate> cryptoPrice(Crypto crypto) {
    log.info("Updating price for: {}", crypto);
    return Flux.interval(Duration.ofSeconds(10))
        .map(val -> fromPrice(cryptoPriceService.getCurrentPrice(crypto)));
  }
}
