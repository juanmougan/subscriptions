package com.juanmougan.examples.graphql.subscriptions.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Price {
  BigDecimal price;
  Crypto type;
}
