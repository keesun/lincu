package io.lincu.domains;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Keeun Baik
 */
@Data
@Entity
@Table(name = "d2_settings")
public class Settings {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "`key`")
    private String key;

    private String value;

}
