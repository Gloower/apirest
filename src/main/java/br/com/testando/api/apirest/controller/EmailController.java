package br.com.testando.api.apirest.controller;

import br.com.testando.api.apirest.model.EmailDetails;
import br.com.testando.api.apirest.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired private EmailService emailService;

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody EmailDetails emailDetails)
    {
        String status
                = emailService.sendMailWithAttachment(emailDetails);

        return status;
    }
}
