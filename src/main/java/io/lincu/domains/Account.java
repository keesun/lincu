package io.lincu.domains;

import lombok.Data;

/**
 * @author Keeun Baik
 */
@Data
public class Account {

    Long id;

    String username;

    String email;

    String password;

    String slug; // for url path

    String location;

    String website;

    String bio;

    boolean owner;

}
