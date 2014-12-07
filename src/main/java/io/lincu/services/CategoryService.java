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
}
