package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.model.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicRepository extends JpaRepository<Characteristic,Long> {
}
