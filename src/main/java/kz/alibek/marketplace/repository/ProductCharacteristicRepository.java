package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.model.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic,Long> {
}
