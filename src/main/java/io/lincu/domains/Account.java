package io.lincu.domains;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Accounts that can login to D2.
 *
 * @author Keeun Baik
 */
@Data
@Entity
@Table(name = "d2_accounts")
public class Account {

    @Id @GeneratedValue
    Long id;

    String username; // for display

    @Column(nullable = false)
    @Email
    @NotEmpty
    String email;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 5)
    String password;

    String slug; // for url path

    String location;

    String website;

    @Lob
    String bio;

    boolean owner;

    @Temporal(TemporalType.TIMESTAMP)
    Date created;

    @Temporal(TemporalType.TIMESTAMP)
    Date updated;

}
