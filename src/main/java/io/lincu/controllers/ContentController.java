package io.lincu.controllers;

import io.lincu.controllers.dtos.ContentDTO;
import io.lincu.domains.Content;
import io.lincu.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/**
 * @author Keeun Baik
 */
@Controller
public class ContentController {

    @Autowired RestTemplate restTemplate;

    @Autowired ContentService service;

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
        if(result.hasErrors()) {
            return "/index";
        }
        Content newContent = service.addNew(content);
        if(!newContent.isAlive()) {
            return "/index";
        }
        return "redirect:/";
    }



}
