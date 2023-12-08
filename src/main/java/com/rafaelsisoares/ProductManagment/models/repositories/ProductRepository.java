package com.rafaelsisoares.ProductManagment.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rafaelsisoares.ProductManagment.models.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
