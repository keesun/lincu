package io.lincu.controllers.dtos;

import lombok.Data;

import java.util.Date;

/**
 * @author Keeun Baik
 */
@Data
public class ContentDTO {

    private Long id;

    private PostDTO post;

    private CategoryDTO category;

    private Date curatedAt;

}
