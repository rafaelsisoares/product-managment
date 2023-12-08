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
import com.rafaelsisoares.ProductManagment.controllers.dto.BrandDTO;
import com.rafaelsisoares.ProductManagment.controllers.dto.ResponseDTO;
import com.rafaelsisoares.ProductManagment.models.entities.Brand;
import com.rafaelsisoares.ProductManagment.services.BrandService;

@RestController
@RequestMapping("/brandies")
public class BrandController {
  private BrandService service;

  public BrandController(BrandService service) {
    this.service = service;
  }

  @GetMapping()
  public List<BrandDTO> getAllBrandies() {
    return service.getAllBrandies().stream()
        .map(brand -> new BrandDTO(brand.getId(), brand.getName())).collect(Collectors.toList());
  }

  @PostMapping()
  public ResponseEntity<ResponseDTO<Brand>> createBrand(@RequestBody BrandDTO brandDTO) {
    Brand newBrand = service.createBrand(brandDTO.toBrand());
    ResponseDTO<Brand> response = new ResponseDTO<>("Marca cadastrada!", newBrand);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDTO<Brand>> updateBrand(@PathVariable Long id,
      @RequestBody BrandDTO brandDTO) {
    Optional<Brand> updatedBrand = service.updateBrand(id, brandDTO.toBrand());
    if (updatedBrand.isEmpty()) {
      ResponseDTO<Brand> response = new ResponseDTO<>("Marca não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Brand> response = new ResponseDTO<>("Marca atualizada!", updatedBrand.get());
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO<Brand>> deleteBrand(@PathVariable Long id) {
    Optional<Brand> deletedBrand = service.deleteBrand(id);
    if (deletedBrand.isEmpty()) {
      ResponseDTO<Brand> response = new ResponseDTO<>("Marca não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Brand> response = new ResponseDTO<>("Marca removida!", null);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Brand>> getBrandById(@PathVariable Long id) {
    Optional<Brand> brandFounded = service.getBrandById(id);
    if (brandFounded.isEmpty()) {
      ResponseDTO<Brand> response = new ResponseDTO<>("Marca não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    ResponseDTO<Brand> response = new ResponseDTO<>("Marca encontrada!", brandFounded.get());
    return ResponseEntity.ok(response);
  }
}
