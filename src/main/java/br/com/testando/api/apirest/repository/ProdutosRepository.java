package br.com.testando.api.apirest.repository;

import br.com.testando.api.apirest.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
    public List<Produtos> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
