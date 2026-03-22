package br.com.ueder.agro_labor_api.repositories;

import br.com.ueder.agro_labor_api.entities.turma.Turma;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("select t from Turma t where t.ativo = true and t.empresa.id = :empresa")
    List<Turma> findAll(Long empresa);

    @Query("select t from Turma t where t.ativo = true and t.empresa.id = :empresa and t.id = :id")
    Optional<Turma> findById(@Param("empresa") Long empresa, @Param("id") Long idTurma);

    @Query("select t from Turma t where t.ativo = true and t.empresa.id = :empresa and t.controle = :controle")
    Optional<Turma> findByControle(@Param("empresa") Long tenantId,@Param("controle") String controle);
}
