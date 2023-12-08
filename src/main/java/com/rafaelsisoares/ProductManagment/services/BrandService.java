package com.rafaelsisoares.ProductManagment.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rafaelsisoares.ProductManagment.models.entities.Brand;
import com.rafaelsisoares.ProductManagment.models.repositories.BrandRepository;

@Service
public class BrandService {
  private BrandRepository repository;

  @Autowired
  public BrandService(BrandRepository repository) {
    this.repository = repository;
  }

  public Brand createBrand(Brand brand) {
    return repository.save(brand);
  }

  public List<Brand> getAllBrandies() {
    return repository.findAll();
  }

  public Optional<Brand> getBrandById(Long id) {
    return repository.findById(id);
  }

  public Optional<Brand> updateBrand(Long id, Brand brand) {
    Optional<Brand> brandFounded = repository.findById(id);

    if (brandFounded.isPresent()) {
      Brand brandFromDB = brandFounded.get();
      brandFromDB.setName(brand.getName());
      return Optional.of(repository.save(brandFromDB));
    }
    return brandFounded;
  }

  public Optional<Brand> deleteBrand(Long id) {
    Optional<Brand> brandFounded = repository.findById(id);

    if (brandFounded.isPresent()) {
      repository.deleteById(id);
    }
    return brandFounded;
  }
}
