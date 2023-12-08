package com.rafaelsisoares.ProductManagment.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rafaelsisoares.ProductManagment.models.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
