package io.lincu.repositories;

import io.lincu.domains.ghost.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Keeun Baik
 */
public interface PostsRepository extends JpaRepository<Post, Long> {

}
