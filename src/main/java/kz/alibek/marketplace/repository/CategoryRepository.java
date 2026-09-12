package kz.alibek.marketplace.repository;

import kz.alibek.marketplace.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
