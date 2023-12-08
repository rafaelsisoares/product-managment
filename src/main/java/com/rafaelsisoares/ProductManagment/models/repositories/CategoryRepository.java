package com.rafaelsisoares.ProductManagment.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rafaelsisoares.ProductManagment.models.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
