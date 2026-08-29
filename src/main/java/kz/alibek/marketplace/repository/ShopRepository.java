package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
