package br.com.testando.api.apirest.repository;

import br.com.testando.api.apirest.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Clientes, Long> {
    public List<Clientes> findAllByNomeContainingIgnoreCase(String nome);
    public Optional<Clientes> findByEmail(String email);
}
