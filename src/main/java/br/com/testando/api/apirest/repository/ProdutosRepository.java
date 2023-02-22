package br.com.testando.api.apirest.repository;

import br.com.testando.api.apirest.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
    public List<Produtos> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
