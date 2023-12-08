package com.rafaelsisoares.ProductManagment.controllers.dto;

import com.rafaelsisoares.ProductManagment.models.entities.Brand;

public record BrandDTO(Long id, String name) {
  public Brand toBrand() {
    return new Brand(id, name);
  }
}
