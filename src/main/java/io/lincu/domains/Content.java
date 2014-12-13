package io.lincu.domains;

import io.lincu.domains.ghost.Post;
import io.lincu.domains.ghost.Tag;
import io.lincu.domains.ghost.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Wrapper class of the ghost.posts(Post class).
 * This class contains Post, Post's createdBy(User class), Category and Tags.
 *
 * @author Keeun Baik
 */
@Data
@Entity
@EqualsAndHashCode(of = {"id", "url"})
@Table(name = "d2_contents")
public class Content {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", columnDefinition = "int unsigned")
    private Post post;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Account curatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date curatedAt;

}
