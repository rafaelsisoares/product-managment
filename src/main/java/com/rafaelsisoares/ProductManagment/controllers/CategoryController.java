package com.rafaelsisoares.ProductManagment.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rafaelsisoares.ProductManagment.controllers.dto.CategoryDTO;
import com.rafaelsisoares.ProductManagment.controllers.dto.ResponseDTO;
import com.rafaelsisoares.ProductManagment.models.entities.Category;
import com.rafaelsisoares.ProductManagment.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  private CategoryService service;

  public CategoryController(CategoryService service) {
    this.service = service;
  }

  @GetMapping()
  public List<CategoryDTO> getAllCategories() {
    return service.getAllCategories().stream()
        .map(category -> new CategoryDTO(category.getId(), category.getName()))
        .collect(Collectors.toList());
  }

  @PostMapping()
  public ResponseEntity<ResponseDTO<Category>> createCategory(
      @RequestBody CategoryDTO categoryDTO) {
    Category newCategory = service.createCategory(categoryDTO.toCategory());
    ResponseDTO<Category> response = new ResponseDTO<>("Categoria cadastrada!", newCategory);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDTO<Category>> updateCategory(@PathVariable Long id,
      @RequestBody CategoryDTO categoryDTO) {
    Optional<Category> updatedCategory = service.updateCategory(id, categoryDTO.toCategory());
    if (updatedCategory.isEmpty()) {
      ResponseDTO<Category> response = new ResponseDTO<>("Categoria não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Category> response =
        new ResponseDTO<>("Categoria atualizada!", updatedCategory.get());
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO<Category>> deleteCategory(@PathVariable Long id) {
    Optional<Category> deletedCategory = service.deleteCategory(id);
    if (deletedCategory.isEmpty()) {
      ResponseDTO<Category> response = new ResponseDTO<>("Categoria não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Category> response = new ResponseDTO<>("Categoria removida!", null);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Category>> getCategoryById(@PathVariable Long id) {
    Optional<Category> categoryFounded = service.getCategoryById(id);
    if (categoryFounded.isEmpty()) {
      ResponseDTO<Category> response = new ResponseDTO<>("Categoria não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Category> response =
        new ResponseDTO<>("Categoria encontrada!", categoryFounded.get());
    return ResponseEntity.ok(response);
  }
}
