package br.com.testando.api.apirest.service;

import br.com.testando.api.apirest.model.Clientes;
import br.com.testando.api.apirest.model.ClientesLogin;
import br.com.testando.api.apirest.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Optional<Clientes> cadastrarClientes(Clientes clientes){
        if (clientesRepository.findByEmail(clientes.getEmail())
                .isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado!", null);

        clientes.setSenha(criptografarSenha(clientes.getSenha()));
        return Optional.of(clientesRepository.save(clientes));
    }

    public Optional<Clientes> atualizarClientes(Clientes clientes){
        if (clientesRepository.findById(clientes.getId()).isPresent()){
            Optional<Clientes> buscaClientes = clientesRepository.
                    findByEmail(clientes.getEmail());
            if (buscaClientes.isPresent()){
                if (buscaClientes.get().getId() != clientes.getId())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Email ja cadastrado!", null);
            }
            clientes.setSenha(criptografarSenha(clientes.getSenha()));
            return Optional.of(clientesRepository.save(clientes));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Email ja cadastrado!", null);
    }

    public Optional<ClientesLogin> autenticarClientes(
            Optional<ClientesLogin> clientesLogin){
                Optional<Clientes> clientes = clientesRepository.
                    findByEmail(clientesLogin.get().getEmail());
                if (clientes.isPresent()){
                    if(compararSenhas(clientesLogin.get().getSenha(),
                            clientes.get().getSenha())){
                        clientesLogin.get().setId(clientes.get().getId());
                        clientesLogin.get().setNome(clientes.get().getNome());
                        clientesLogin.get().setEmail(clientes.get().getEmail());
                        clientesLogin.get().setToken(
                                gerarBasicToken(clientesLogin.get().getEmail(),
                                        clientesLogin.get().getSenha()));
                                clientesLogin.get().setSenha(clientes.get().getSenha());
                                return clientesLogin;
                    }
                }
throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalidos!", null);

    }

    private String criptografarSenha(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaEncoder = encoder.encode(senha);
        return senhaEncoder;
    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, senhaBanco);
    }

    private String gerarBasicToken(String email, String password){
        String estrutura = email + ":" + password;
        byte[] estruturaBase64 = Base64.getDecoder().decode(
                estrutura.getBytes(Charset.forName("US-ASCII")));
        return "Basic" + new String(estruturaBase64);
    }
}
