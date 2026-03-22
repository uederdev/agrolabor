package br.com.ueder.agro_labor_api.repositories;

import br.com.ueder.agro_labor_api.entities.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("select e from Empresa e where e.ativo = true")
    List<Empresa> findAll();

    @Query("select e from Empresa e where e.ativo = true and e.id = :id")
    Optional<Empresa> findById(@Param("id") Long id);

    @Query("select e from Empresa e where e.ativo = true and e.cnpj = :cnpj")
    Optional<Empresa> findByCnpj(String cnpj);
}
