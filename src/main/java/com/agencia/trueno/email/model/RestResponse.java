package com.agencia.trueno.email.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestResponse {
    private Integer status;
    private String title;
    private String description;
}
