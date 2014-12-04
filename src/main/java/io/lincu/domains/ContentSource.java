package io.lincu.domains;

import lombok.Data;

/**
 * Represents a source of contents, like Blog, News and Portal.
 * A content source may have a lot of contents.
 *
 * @author Keeun Baik
 */
@Data
public class ContentSource {

    private Long id;

    private String name;

    private String url;

    private SourceType sourceType;

}
