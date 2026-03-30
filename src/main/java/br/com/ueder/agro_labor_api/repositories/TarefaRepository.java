package br.com.ueder.agro_labor_api.repositories;

import br.com.ueder.agro_labor_api.entities.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("select t from Tarefa t where t.ativo = true and t.empresa.id = :empresa")
    List<Tarefa> findAll(@Param("empresa") Long tenantId);

    @Query("select t from Tarefa t where t.ativo = true and t.empresa.id = :empresa and t.id = :id")
    Optional<Tarefa> findById(@Param("empresa") Long tenantId,@Param("id") Long id);

    @Query("select t from Tarefa t where t.ativo = true and t.empresa.id = :empresa and t.controle = :controle")
    Optional<Tarefa> findByControle(@Param("empresa") Long tenantId,@Param("controle") String controle);
}
