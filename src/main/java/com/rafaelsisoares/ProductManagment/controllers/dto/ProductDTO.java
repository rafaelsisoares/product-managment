package com.rafaelsisoares.ProductManagment.controllers.dto;

import com.rafaelsisoares.ProductManagment.models.entities.Product;

public record ProductDTO(Long id, String name, Double price) {
  public Product toProduct() {
    return new Product(id, name, price);
  }
}
