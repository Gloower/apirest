package br.com.testando.api.apirest.service;

import br.com.testando.api.apirest.model.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails emailDetails);

}
