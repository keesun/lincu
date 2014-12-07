package io.lincu.repositories;

import io.lincu.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Keeun Baik
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
