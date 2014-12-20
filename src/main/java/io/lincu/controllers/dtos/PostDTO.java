package io.lincu.controllers.dtos;

import lombok.Data;

/**
 * @author Keeun Baik
 */
@Data
public class PostDTO {

    private Long id;

    private String title;

    private String slug;

    private String image;

    private boolean featured;

}
