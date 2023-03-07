package br.com.testando.api.apirest.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;

}
