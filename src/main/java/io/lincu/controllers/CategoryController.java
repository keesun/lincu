package io.lincu.controllers;

import io.lincu.domains.Category;
import io.lincu.repositories.CategoryRepository;
import io.lincu.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Keeun Baik
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String allCategories(Model model) {
        model.addAttribute("categories", repository.findAll());
        model.addAttribute("category", new Category());
        return "/category/list";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String createNewCategory(@ModelAttribute @Valid Category category, BindingResult result) {
        if(result.hasErrors()) {
            return "/category/list";
        }

        Category existingCategory = repository.findByName(category.getName());
        if(existingCategory != null) {
            result.rejectValue("name", "duplicated", "Already existing name");
            return "redirect:/categories";
        }

        service.addNew(category);
        return "redirect:/categories";
    }

}
