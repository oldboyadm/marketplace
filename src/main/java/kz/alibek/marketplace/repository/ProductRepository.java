package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
