package sandbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Keeun Baik
 */
public class RestTemplateTests {

    @Test
    public void getResponseFromUrl() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://blogs.atlassian.com/2014/12/fun-fridays-introducing-new-era-video/", String.class);
        Document doc = Jsoup.parse(response.getBody());
        Elements title = doc.select("title");
        System.out.println(title.text());
    }
}
