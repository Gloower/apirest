package br.com.testando.api.apirest.repository;

import br.com.testando.api.apirest.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
     public List<Categoria> findAllByTipoPerifericoContainingIgnoreCase(@Param("tipoPeriferico")String tipoPeriferico);
}
