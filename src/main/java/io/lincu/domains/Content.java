package io.lincu.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Keeun Baik
 */
@Data
@Entity
public class Content {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @URL
    @NotEmpty
    private String url;

    private String title;

    @ManyToOne
    private ContentSource contentSource;

    private Long viewCount;

    @ManyToOne
    private Category category;

    @ManyToMany
    private Set<Tag> tags;

    @ManyToOne
    private Account curatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date curatedAt;

    private boolean alive;

}
