package io.lincu.controllers;

import io.lincu.controllers.dtos.ContentDTO;
import io.lincu.domains.Category;
import io.lincu.domains.Content;
import io.lincu.repositories.CategoryRepository;
import io.lincu.repositories.ContentRepository;
import io.lincu.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Keeun Baik
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService service;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/contents/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity check(@RequestParam String contentUrl) {
        ContentDTO.ResponseToCheck dto = service.checkUrl(contentUrl);
        if(dto.isValid()) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/contents", method = RequestMethod.POST)
    public String addContent(@ModelAttribute @Valid Content content, BindingResult result) {
        throw new UnsupportedOperationException();
    }

    @RequestMapping(value = "/contents", method = RequestMethod.GET)
    public String allContents(Model model) {
        addModelsForContents(model);

        List<Content> all = service.getAllContents();
        model.addAttribute("allContents", all);

        if(all.size() > 0) {
            Content firstContent = all.get(0);
            model.addAttribute("current", firstContent);
        }

        return "/index";
    }

    @RequestMapping(value = "/contents/{id}", method = RequestMethod.GET)
    public String viewContents(@PathVariable Long id, Model model) {
        addModelsForContents(model);

        List<Content> all = contentRepository.findAll(sortByCuratedDEAC());
        model.addAttribute("allContents", all);

        Content content = contentRepository.findOne(id);
        model.addAttribute("current", content);

        return "/index";
    }

    @RequestMapping(value = "/contents/{id}", method = RequestMethod.DELETE)
    public String deleteContent(@PathVariable Long id) {
        contentRepository.delete(id);
        return "redirect:/contents";
    }

    private Sort sortByCuratedDEAC() {
        return new Sort(Sort.Direction.DESC, "curatedAt");
    }

    private void addModelsForContents(Model model) {
        Category uncategorized = categoryRepository.findByName(Category.UNCATEGORIZED);

        Content newContent = new Content();
        newContent.setCategory(uncategorized);

        model.addAttribute("content", newContent);

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
    }

}
