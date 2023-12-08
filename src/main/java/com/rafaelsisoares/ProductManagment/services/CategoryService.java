package com.rafaelsisoares.ProductManagment.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rafaelsisoares.ProductManagment.models.entities.Category;
import com.rafaelsisoares.ProductManagment.models.repositories.CategoryRepository;

@Service
public class CategoryService {
  private CategoryRepository repository;

  @Autowired
  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public Category createCategory(Category category) {
    return repository.save(category);
  }

  public List<Category> getAllCategories() {
    return repository.findAll();
  }

  public Optional<Category> getCategoryById(Long id) {
    return repository.findById(id);
  }

  public Optional<Category> updateCategory(Long id, Category category) {
    Optional<Category> categoryFounded = repository.findById(id);

    if (categoryFounded.isPresent()) {
      Category categoryFromDB = categoryFounded.get();
      categoryFromDB.setName(category.getName());
      return Optional.of(repository.save(categoryFromDB));
    }
    return categoryFounded;
  }

  public Optional<Category> deleteCategory(Long id) {
    Optional<Category> categoryFounded = repository.findById(id);

    if (categoryFounded.isPresent()) {
      repository.deleteById(id);
    }
    return categoryFounded;
  }

}
