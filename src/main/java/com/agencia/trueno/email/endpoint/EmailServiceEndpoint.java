package com.agencia.trueno.email.endpoint;

import java.time.Instant;

import com.agencia.trueno.email.model.EmailFormRequest;
import com.agencia.trueno.email.model.EmailRestResponse;
import com.agencia.trueno.email.model.RestResponse;
import com.agencia.trueno.email.service.FormProcessingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api("Bundle (offer) management")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailServiceEndpoint {

    private FormProcessingService formProcessingService;

    @ApiOperation(value = "Retrieve bundles along with its elements")
    @PostMapping(produces = APPLICATION_JSON_VALUE, path = "/bundles", params = {"appToken", "uid"})
    public RestResponse getBundlesWithModifiers(
        @RequestBody EmailFormRequest request) {
        return formProcessingService.processRequest(request);
    }
}