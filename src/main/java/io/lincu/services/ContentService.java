package io.lincu.services;

import io.lincu.domains.Content;
import io.lincu.domains.ghost.Post;
import io.lincu.repositories.CategoryRepository;
import io.lincu.repositories.ContentRepository;
import io.lincu.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Keeun Baik
 */
@Service
@Transactional
public class ContentService {

    @Autowired
    private ContentRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostsRepository postsRepository;

    public Content save(Long postId, Long categoryId) {
        Post post = postsRepository.findOne(postId);

        Content content = repository.findByPost(post);
        if (content == null) {
            content = new Content();
            content.setPost(post);
        }

        content.setCategory(categoryRepository.findOne(categoryId));
        content.setCuratedAt(new Date());
        // TODO set admin as a cureator

        Content newContent = repository.save(content);
        post.setContent(newContent);

        return newContent;
    }
}
