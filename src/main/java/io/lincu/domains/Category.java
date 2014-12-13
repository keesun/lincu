package io.lincu.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Keeun Baik
 */
@Data
@Entity
@EqualsAndHashCode(of = {"id", "name"})
@ToString(exclude = "contents")
@Table(name = "d2_categories")
public class Category {

    public static final String UNCATEGORIZED = "Uncategorized";

    public static final String NEWS = "News";

    public static final String EVENTS = "Events";

    public static final String TECHNICAL_WRITINGS = "Technical Writings";

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
