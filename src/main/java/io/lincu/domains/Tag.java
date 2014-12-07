package io.lincu.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Keeun Baik
 */
@Data
@Entity
public class Tag {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String descr;

    @ManyToMany(mappedBy = "tags")
    private Set<Content> contents;

}
