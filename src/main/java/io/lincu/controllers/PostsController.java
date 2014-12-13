package io.lincu.controllers;

import io.lincu.domains.ghost.Post;
import io.lincu.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Keeun Baik
 */
@Controller
public class PostsController {

    @Autowired
    private PostsRepository repository;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String allPosts(Model model) {
        List<Post> all = repository.findAll();
        model.addAttribute("posts", all);
        if (all.size() > 0) {
            model.addAttribute("current", all.get(0));
        }
        return "/posts/index";
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable Long id, Model model) {
        List<Post> all = repository.findAll();
        model.addAttribute("posts", all);
        model.addAttribute("current", repository.findOne(id));
        return "/posts/index";
    }

}
