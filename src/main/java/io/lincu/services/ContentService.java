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
import java.util.Date;

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
        ContentDTO.ResponseToCheck dto = new ContentDTO.ResponseToCheck();
        dto.setContentUrl(contentUrl);

        Content content = repository.findByUrl(contentUrl);
        if(content != null) {
            dto.setValid(false);
            dto.setReason("Duplicated with /contents/" + content.getId());
            return dto;
        }

        ResponseEntity<String> contentResponse = null;
        try {
            contentResponse = restTemplate.getForEntity(contentUrl, String.class);
        } catch (Exception e) {
            dto.setValid(false);
            dto.setReason("Request failed to get response");
            return dto;
        }

        HttpStatus statusCode = contentResponse.getStatusCode();
        if(statusCode.equals(HttpStatus.OK)) {
            dto.setValid(true);
            dto.setTitle(parsingTitle(contentResponse.getBody()));
        }

        return dto;
    }

    private String parsingTitle(String body) {
        Document doc = Jsoup.parse(body);
        Elements title = doc.select("title");
        return title.text();
    }

    public Content addNew(Content content) {
        ResponseEntity<String> contentResponse = null;
        try {
            contentResponse = restTemplate.getForEntity(content.getUrl(), String.class);
        } catch (Exception e) {
            content.setAlive(false);
            return content;
        }

        String body = contentResponse.getBody();
        Document doc = Jsoup.parse(body);

        Elements title = doc.select("title");
        content.setTitle(title.text());

        Elements description = doc.select("meta[property=og:description]");
        content.setDescription(description.attr("content"));

        Elements siteName = doc.select("meta[property=og:site_name]");
        content.setSiteName(siteName.attr("content"));

        content.setAlive(true);
        content.setCuratedAt(new Date());
        return repository.save(content);
    }

}
