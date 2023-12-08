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
import com.rafaelsisoares.ProductManagment.controllers.dto.ProductDTO;
import com.rafaelsisoares.ProductManagment.controllers.dto.ResponseDTO;
import com.rafaelsisoares.ProductManagment.models.entities.Product;
import com.rafaelsisoares.ProductManagment.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
  private ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping()
  public List<ProductDTO> getAllProducts() {
    return service.getAllProducts().stream()
        .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
        .collect(Collectors.toList());
  }

  @PostMapping()
  public ResponseEntity<ResponseDTO<Product>> createProduct(@RequestBody ProductDTO productDTO) {
    Product newProduct = service.createProduct(productDTO.toProduct());
    ResponseDTO<Product> response = new ResponseDTO<>("Produto cadastrado!", newProduct);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDTO<Product>> updateProduct(@PathVariable Long id,
      @RequestBody ProductDTO productDTO) {
    Optional<Product> updatedProduct = service.updateProduct(id, productDTO.toProduct());
    if (updatedProduct.isEmpty()) {
      ResponseDTO<Product> response = new ResponseDTO<>("Produto não encontrado", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Product> response = new ResponseDTO<>("Produto atualizado!", updatedProduct.get());
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO<Product>> deleteProduct(@PathVariable Long id) {
    Optional<Product> deletedProduct = service.deleteProduct(id);
    if (deletedProduct.isEmpty()) {
      ResponseDTO<Product> response = new ResponseDTO<>("Produto não encontrado", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Product> response = new ResponseDTO<>("Produto removido!", null);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Product>> getProductById(@PathVariable Long id) {
    Optional<Product> productFounded = service.getProductById(id);
    if (productFounded.isEmpty()) {
      ResponseDTO<Product> response = new ResponseDTO<>("Produto não encontrado", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Product> response = new ResponseDTO<>("Produto encontrado!", productFounded.get());
    return ResponseEntity.ok(response);
  }
}
