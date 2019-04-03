package com.agencia.trueno.email.service;

import com.agencia.trueno.email.model.EmailFormRequest;
import com.agencia.trueno.email.model.RestResponse;
import com.agencia.trueno.email.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormProcessingService {

    private final EmailService emailService;
    private final FormRepository formRepository;

    public RestResponse processRequest(EmailFormRequest request) {
        try {
            emailService.sendUserEmail(request);
            emailService.sendOrganizationEmail(request);
            formRepository.storeFormInfo(request);

            return RestResponse.builder()
                .status(200)
                .description("Emails were sent and form data was stored.")
                .title("Request processed successfully")
                .build();
        } catch (Exception e) {
            return RestResponse.builder()
                .status(400)
                .description(e.getMessage())
                .title("Error processing the request")
                .build();
        }
    }
}
