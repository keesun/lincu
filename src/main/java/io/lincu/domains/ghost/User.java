package io.lincu.domains.ghost;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Read only domain for ghost.users table
 *
 * @author Keeun Baik
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String slug;

    private String email;

    private String image;

    private String cover;

    private String bio;

    private String website;

}
