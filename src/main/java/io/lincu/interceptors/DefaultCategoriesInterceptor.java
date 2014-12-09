package io.lincu.interceptors;

import io.lincu.domains.Category;
import io.lincu.repositories.CategoryRepository;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        addIfNotExist("News");
        addIfNotExist("Events");
        addIfNotExist("Technical Writings");
        return true;
    }

    private void addIfNotExist(String categoryName) {
        Category news = categoryRepository.findByName(categoryName);
        if(news == null) {
            news = new Category();
            news.setName(categoryName);
            categoryService.addNew(news);
        }
    }
}
