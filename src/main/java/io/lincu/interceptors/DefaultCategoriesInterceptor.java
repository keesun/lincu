package io.lincu.interceptors;

import io.lincu.domains.Category;
import io.lincu.domains.Settings;
import io.lincu.repositories.CategoryRepository;
import io.lincu.repositories.SettingsRepository;
import io.lincu.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Keeun Baik
 */
@Component
public class DefaultCategoriesInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Settings categoriesInitialized = settingsRepository.findByKey("init-categories");
        if (categoriesInitialized == null) {
            addIfNotExist(Category.UNCATEGORIZED);
            addIfNotExist(Category.NEWS);
            addIfNotExist(Category.EVENTS);
            addIfNotExist(Category.TECHNICAL_WRITINGS);

            categoriesInitialized = new Settings();
            categoriesInitialized.setKey("init-categories");
            categoriesInitialized.setValue("done");
            settingsRepository.save(categoriesInitialized);
        }

        return true;
    }

    private void addIfNotExist(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if(category == null) {
            category = new Category();
            category.setName(categoryName);
            if(categoryName.equals(Category.UNCATEGORIZED)) {
                category.setEditable(false);
            } else {
                category.setEditable(true);
            }
            categoryService.addNew(category);
        }
    }
}
