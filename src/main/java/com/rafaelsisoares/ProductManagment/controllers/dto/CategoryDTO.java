package com.rafaelsisoares.ProductManagment.controllers.dto;

import com.rafaelsisoares.ProductManagment.models.entities.Category;

public record CategoryDTO(Long id, String name) {
  public Category toCategory() {
    return new Category(id, name);
  }
}
