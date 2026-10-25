package kz.alibek.marketplace.conroller;

import kz.alibek.marketplace.dto.category.CategoryRequestDto;
import kz.alibek.marketplace.dto.category.CategoryResponseDto;
import kz.alibek.marketplace.dto.category.SubcategoryRequestDto;
import kz.alibek.marketplace.dto.category.SubcategoryResponseDto;
import kz.alibek.marketplace.exception.NotFoundException;
import kz.alibek.marketplace.model.Category;
import kz.alibek.marketplace.model.Characteristic;
import kz.alibek.marketplace.model.Subcategory;
import kz.alibek.marketplace.repository.CategoryRepository;
import kz.alibek.marketplace.repository.CharacteristicRepository;
import kz.alibek.marketplace.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CharacteristicRepository characteristicRepository;

    @PostMapping
    public CategoryResponseDto create (@RequestBody CategoryRequestDto dto){
        Category category = new Category();
        category.setName(dto.name());


        for (SubcategoryRequestDto subcategoryDto : dto.subcategories()){
            Subcategory subcategory = new Subcategory();
            subcategory.setName(subcategoryDto.name());
            subcategory.setCategory(category);

            for (String chrctr : subcategoryDto.characteristics()){
                Characteristic characteristic = new Characteristic();
                characteristic.setName(chrctr);
                characteristic.setSubcategory(subcategory);
                subcategory.getCharacteristics().add(characteristic);
            }
            category.getSubcategories().add(subcategory);
        }
        categoryRepository.save(category);

        return CategoryResponseDto.of(category);
    }

    @PostMapping("/{id}/subcategories")
    public SubcategoryResponseDto create (@PathVariable Long id, @RequestBody SubcategoryRequestDto dto){
        Subcategory subcategory = new Subcategory();
        subcategory.setName(dto.name());
        subcategory.setCategory(categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория не найдена")));

        for (String chrctr : dto.characteristics()){
            Characteristic characteristic = new Characteristic();
            characteristic.setName(chrctr);
            characteristic.setSubcategory(subcategory);
            subcategory.getCharacteristics().add(characteristic);
        }
        subcategoryRepository.save(subcategory);

        return SubcategoryResponseDto.of(subcategory);
    }

    @GetMapping
    public List<CategoryResponseDto> findAll (){
        return categoryRepository.findAll().stream()
                .map(category -> CategoryResponseDto.withoutCharacteristics(category)).toList();
    }

    @GetMapping("/{id}/subcategories")
        public List<SubcategoryResponseDto> findSubcategories (@PathVariable Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория не найдена"));
        return category.getSubcategories().stream().map(SubcategoryResponseDto::of).toList();
    }

    @GetMapping("/{id}")
    public CategoryResponseDto findById(@PathVariable Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория не найдена"));
        return CategoryResponseDto.of(category);
    }
}
