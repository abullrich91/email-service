package com.agencia.trueno.email.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmailFormRequest {
    private String completeName;
    private String organization;
    private String phone;
    private String email;
    private String message;
}
