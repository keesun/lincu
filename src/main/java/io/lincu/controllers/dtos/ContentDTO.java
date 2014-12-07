package io.lincu.controllers.dtos;

import lombok.Data;

/**
 * @author Keeun Baik
 */
@Data
public class ContentDTO {

    protected String contentUrl;

    @Data
    public static class RequestToCheck extends ContentDTO {

    }

    @Data
    public static class ResponseToCheck extends ContentDTO {

        private String title;

        private boolean valid;

        private String reason;

    }

}
