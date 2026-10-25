package kz.alibek.marketplace.conroller;

import kz.alibek.marketplace.dto.category.SubcategoryResponseDto;
import kz.alibek.marketplace.dto.product.ProductCharacteristicRequestDto;
import kz.alibek.marketplace.dto.product.ProductCharacteristicResponseDto;
import kz.alibek.marketplace.dto.product.ProductRequestDto;
import kz.alibek.marketplace.dto.product.ProductResponseDto;
import kz.alibek.marketplace.exception.NotFoundException;
import kz.alibek.marketplace.model.Characteristic;
import kz.alibek.marketplace.model.Product;
import kz.alibek.marketplace.model.ProductCharacteristic;
import kz.alibek.marketplace.model.Subcategory;
import kz.alibek.marketplace.repository.CharacteristicRepository;
import kz.alibek.marketplace.repository.ProductCharacteristicRepository;
import kz.alibek.marketplace.repository.ProductRepository;
import kz.alibek.marketplace.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final CharacteristicRepository characteristicRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ProductCharacteristicRepository productCharacteristicRepository;

    @PostMapping
    public ProductResponseDto create (@RequestBody ProductRequestDto dto){
        Subcategory subcategory = subcategoryRepository.findById(dto.subcategoryId())
                .orElseThrow(() -> new NotFoundException("Подкатегория не найдена"));


        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setSubcategory(subcategory);
        product.setPrice(dto.price());
        product.setPhotoUrl(dto.photoUrl());

        for(ProductCharacteristicRequestDto characteristicDto : dto.characteristics()){
            ProductCharacteristic productCharacteristic = new ProductCharacteristic();
            productCharacteristic.setProduct(product);
            Characteristic characteristic = characteristicRepository.findById(characteristicDto.characteristicId())
                    .orElseThrow(() -> new NotFoundException("Характеристика не найдена"));

            productCharacteristic.setCharacteristic(characteristic);
            productCharacteristic.setValue(characteristicDto.value());
            product.getProductCharacteristics().add(productCharacteristic);
        }


         productRepository.save(product);
         return ProductResponseDto.of(product);
    }
    @GetMapping
    public List<ProductResponseDto> findAll (@RequestParam(required = false) Long subcategoryId){
        if (subcategoryId != null){
           return productRepository.findBySubcategoryId(subcategoryId).stream().map(ProductResponseDto::of).toList();
        }
        return productRepository.findAll().stream().map(ProductResponseDto::of).toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDto findById (@PathVariable Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Продукт не найден"));
        return ProductResponseDto.of(product);
    }

   /* @GetMapping
    public List<ProductResponseDto> findByCategoryId(@RequestParam Long categoryId){
       return productRepository.findByCategoryId(categoryId).stream().map(ProductResponseDto::of).toList();
    }*/
}