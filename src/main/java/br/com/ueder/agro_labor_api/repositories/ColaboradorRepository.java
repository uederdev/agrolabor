package br.com.ueder.agro_labor_api.repositories;

import br.com.ueder.agro_labor_api.entities.colaborador.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    
    @Query("select c from Colaborador c join fetch c.turma t where c.ativo = true and c.empresa.id = :tenantId")
    List<Colaborador> findAll(Long tenantId);

    @Query("select c from Colaborador c join fetch c.turma t where c.ativo = true and c.empresa.id = :tenantId and c.turma.id = :id")
    List<Colaborador> findByTurmaId(Long tenantId, Long id);

    @Query("select c from Colaborador c join fetch c.turma t where c.ativo = true and c.empresa.id = :tenantId and c.id = :id")
    Optional<Colaborador> findById(Long tenantId, Long id);

    @Query("select c from Colaborador c join fetch c.turma t where c.ativo = true and c.empresa.id = :tenantId and c.matricula = :matricula")
    Optional<Colaborador> findByMatricula(Long tenantId, String matricula);
}
