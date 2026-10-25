package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.dto.product.ProductResponseDto;
import kz.alibek.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findBySubcategoryId(Long id);
    /*public List<Product> findByCategoryId(Long id)*/;
}
