package io.lincu.services;

import io.lincu.controllers.dtos.ContentDTO;
import io.lincu.domains.Content;
import io.lincu.repositories.ContentRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Keeun Baik
 */
@Service
@Transactional
public class ContentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ContentRepository repository;

    public ContentDTO.ResponseToCheck checkUrl(String contentUrl) {
        throw new UnsupportedOperationException();
    }

    private String parsingTitle(String body) {
        Document doc = Jsoup.parse(body);
        Elements title = doc.select("title");
        return title.text();
    }

    public Content addNew(Content content) {
        throw new UnsupportedOperationException();
    }

    public List<Content> getAllContents() {

        return null;
    }
}
