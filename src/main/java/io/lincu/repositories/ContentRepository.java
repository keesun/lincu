package io.lincu.repositories;

import io.lincu.domains.Content;
import io.lincu.domains.ghost.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Keeun Baik
 */
public interface ContentRepository extends JpaRepository<Content, Long> {

    Content findByPost(Post post);
}
