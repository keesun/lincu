package io.lincu.controllers;

import io.lincu.domains.Category;
import io.lincu.domains.ghost.Post;
import io.lincu.repositories.CategoryRepository;
import io.lincu.repositories.PostsRepository;
import io.lincu.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Keeun Baik
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryService service;

    @Autowired
    private PostsRepository postsRepository;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String allCategories(Model model) {
        List<Category> all = repository.findAll();
        model.addAttribute("categories", all);
        model.addAttribute("category", new Category());
        if (all.size() > 0) {
            Category category = all.get(0);
            model.addAttribute("current", category);

            if(category.getName().equals(Category.UNCATEGORIZED)) {
                category = null;
            }
            List<Post> byContentCategory = postsRepository.findByContentCategory(category, publishedAtDesc());
            model.addAttribute("posts", byContentCategory);
        }
        return "/categories/index";
    }

    private Sort publishedAtDesc() {
        return new Sort(Sort.Direction.DESC, "publishedAt");
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String createNewCategory(@ModelAttribute @Valid Category category, BindingResult result) {
        if(result.hasErrors()) {
            return "/categories/index";
        }

        Category existingCategory = repository.findByName(category.getName());
        if(existingCategory != null) {
            result.rejectValue("name", "duplicated", "Already existing name");
            return "redirect:/categories";
        }

        service.addNew(category);
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public String viewCategory(@PathVariable Long id, Model model) {
        model.addAttribute("categories", repository.findAll());
        model.addAttribute("category", new Category());

        Category category = repository.findOne(id);
        model.addAttribute("current", category);

        if(category.getName().equals(Category.UNCATEGORIZED)) {
            category = null;
        }
        List<Post> byContentCategory = postsRepository.findByContentCategory(category, publishedAtDesc());
        model.addAttribute("posts", byContentCategory);

        return "/categories/index";
    }

}
