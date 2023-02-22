package br.com.testando.api.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity
@Table(name = "TB_CLIENTES")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "O atributo Nome é obrigatório!")
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull(message = "O usuario é obrigatório preencher este campo!")
    @Email(message = "O usuario deve preencher um email valido!")
    private String email;

    @NotBlank(message = "Insira uma senha com no mínimo 8 caracteres.")
    @Size(min = 8)
    private String senha;

    @OneToMany(mappedBy = "clientes", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("clientes")
    private List<Produtos> produtos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
