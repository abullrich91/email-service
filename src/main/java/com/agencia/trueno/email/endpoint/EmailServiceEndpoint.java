package com.agencia.trueno.email.endpoint;

import com.agencia.trueno.email.model.EmailFormRequest;
import com.agencia.trueno.email.model.RestResponse;
import com.agencia.trueno.email.service.FormProcessingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Email Service")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailServiceEndpoint {

    private final FormProcessingService formProcessingService;

    @CrossOrigin
    @ApiOperation(value = "Process form data and send emails")
    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/process-form")
    public RestResponse getBundlesWithModifiers(
        @RequestBody EmailFormRequest request) {
        return formProcessingService.processRequest(request);
    }
}
