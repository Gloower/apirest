package br.com.testando.api.apirest.controller;


import br.com.testando.api.apirest.model.Produtos;
import br.com.testando.api.apirest.repository.CategoriaRepository;
import br.com.testando.api.apirest.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produtos>> getAll() {
        return ResponseEntity.ok(produtosRepository.findAll());
    }
}
