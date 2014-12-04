package io.lincu.domains;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author Keeun Baik
 */
@Data
public class Content {

    private Long id;

    private String url;

    private ContentSource contentSource;

    private Long viewCount;

    private Category category;

    private Set<Tag> tags;

    private Account curatedBy;

    private Date curatedAt;

}
