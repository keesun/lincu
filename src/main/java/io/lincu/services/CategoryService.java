package io.lincu.services;

import io.lincu.domains.Category;
import io.lincu.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Keeun Baik
 */
@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public void addNew(Category category) {
        repository.save(category);
    }

    public void update(Category category) {
        if (category.isEditable()) {
            repository.save(category);
        }
    }

    public void delete(Category category) {
        if (category.isEditable()) {
            Category uncategorized = repository.findByName(Category.UNCATEGORIZED);
            category.getContents().parallelStream().forEach(c -> c.setCategory(uncategorized));
            category.setContents(null);
            repository.delete(category);
        }
    }
}
