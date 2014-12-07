package io.lincu.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a source of contents, like Blog, News and Portal.
 * A content source may have a lot of contents.
 *
 * @author Keeun Baik
 */
@Data
@Entity
public class ContentSource {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String url;

    @Enumerated(EnumType.STRING)
    private SourceType sourceType;

    @OneToMany(mappedBy = "contentSource")
    private Set<Content> contents;

}
