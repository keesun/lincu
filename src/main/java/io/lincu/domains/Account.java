package io.lincu.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author Keeun Baik
 */
@Data
@Entity
public class Account {

    @Id @GeneratedValue
    Long id;

    String username; // for display

    String email;

    String password;

    String slug; // for url path

    String location;

    String website;

    @Lob
    String bio;

    boolean owner;

}
