package io.lincu.controllers;

import io.lincu.controllers.dtos.ContentDTO;
import io.lincu.domains.Content;
import io.lincu.services.ContentService;
import org.hibernate.annotations.SourceType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Keeun Baik
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/contents", method = RequestMethod.POST)
    public ResponseEntity newContent(@RequestParam Long postId,
                                           @RequestParam Long categoryId) {
        Content content = service.save(postId, categoryId);
        return new ResponseEntity<>(modelMapper.map(content, ContentDTO.class), HttpStatus.OK);
    }

}
