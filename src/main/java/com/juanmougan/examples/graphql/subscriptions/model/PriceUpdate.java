package com.juanmougan.examples.graphql.subscriptions.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PriceUpdate {

  LocalDateTime timestamp;
  BigDecimal price;
  Crypto type;
}
