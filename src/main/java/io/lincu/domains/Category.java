package io.lincu.domains;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Keeun Baik
 */
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    private String descr;

    @OneToMany(mappedBy = "category")
    private Set<Content> contents;

}
