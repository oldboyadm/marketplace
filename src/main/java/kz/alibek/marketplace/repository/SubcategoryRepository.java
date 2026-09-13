package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
}
