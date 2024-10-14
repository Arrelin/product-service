package com.arrelin.product.dto;

import java.math.BigDecimal;

public record ProductRequest(Long id, String name, String description, BigDecimal price) {

}
