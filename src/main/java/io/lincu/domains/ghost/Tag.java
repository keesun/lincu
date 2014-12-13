package io.lincu.domains.ghost;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Read only domain for ghost.tags table
 *
 * @author Keeun Baik
 */
@Data
@Entity
@Table(name = "tags")
public class Tag {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String slug;

}
