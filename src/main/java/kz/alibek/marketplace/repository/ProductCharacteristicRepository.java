package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.model.ProductsCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCharacteristicRepository extends JpaRepository<ProductsCharacteristic,Long> {
}
