package com.rafaelsisoares.ProductManagment.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rafaelsisoares.ProductManagment.models.entities.Product;
import com.rafaelsisoares.ProductManagment.models.repositories.ProductRepository;

@Service
public class ProductService {
  private ProductRepository repository;

  @Autowired
  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public Product createProduct(Product product) {
    return repository.save(product);
  }

  public Optional<Product> updateProduct(Long id, Product product) {
    Optional<Product> productFounded = repository.findById(id);

    if (productFounded.isEmpty()) {
      return productFounded;
    }
    Product productFromDB = productFounded.get();
    productFromDB.setName(product.getName());
    productFromDB.setPrice(product.getPrice());

    return Optional.of(repository.save(productFromDB));
  }

  public Optional<Product> deleteProduct(Long id) {
    Optional<Product> productFounded = repository.findById(id);

    if (productFounded.isPresent()) {
      repository.deleteById(id);
    }
    return productFounded;
  }

  public Optional<Product> getProductById(Long id) {
    return repository.findById(id);
  }

  public List<Product> getAllProducts() {
    return repository.findAll();
  }
}
