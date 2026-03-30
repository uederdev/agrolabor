package br.com.ueder.agro_labor_api.repositories;

import br.com.ueder.agro_labor_api.entities.proprietario.Proprietario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    @Query("select p from Proprietario p where p.ativo = true and p.empresa.id = :empresa")
    List<Proprietario> findAll(@Param("empresa") Long tenantId);

    @Query("select p from Proprietario p where p.ativo = true and p.empresa.id = :empresa and p.id = :id")
    Optional<Proprietario> findById(Long tenantId, Long id);

    @Query("select p from Proprietario p where p.ativo = true and p.empresa.id = :empresa and p.cpf = :cpf")
    Optional<Proprietario> findByCPF(@Param("empresa") Long tenantId,@Param("cpf") String cpf);

    @Query("select p from Proprietario p where p.ativo = true and p.empresa.id = :empresa and p.controle = :controle")
    Optional<Proprietario> findByControle(@Param("empresa") Long tenantId,@Param("controle") @NotBlank String controle);
}
