package br.com.testando.api.apirest.controller;

import br.com.testando.api.apirest.model.ClientesLogin;
import br.com.testando.api.apirest.model.Clientes;
import br.com.testando.api.apirest.repository.ClientesRepository;
import br.com.testando.api.apirest.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientesController {

    @Autowired ClientesService clientesService;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Clientes>> getAll() {

        return ResponseEntity.ok(clientesRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable Long id){
        return clientesRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<ClientesLogin> login(@RequestBody Optional<ClientesLogin> clientesLogin){
        return clientesService.autenticarClientes(clientesLogin)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/register")
    public ResponseEntity<Clientes> postClientes(@Valid @RequestBody Clientes clientes) {
        return clientesService.cadastrarClientes(clientes)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Clientes> putClientes(@Valid @RequestBody Clientes clientes) {
        return clientesService.atualizarClientes(clientes)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
