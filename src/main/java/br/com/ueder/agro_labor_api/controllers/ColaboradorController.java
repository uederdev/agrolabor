package br.com.ueder.agro_labor_api.controllers;

import br.com.ueder.agro_labor_api.dtos.colaborador.DadosColaborador;
import br.com.ueder.agro_labor_api.dtos.colaborador.DadosColaboradorCreate;
import br.com.ueder.agro_labor_api.dtos.colaborador.DadosColaboradorUpdate;
import br.com.ueder.agro_labor_api.services.ColaboradorService;
import br.com.ueder.agro_labor_api.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/colaboradores")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosColaborador>> findAll(HttpServletRequest request) {
        Long tenantId = Util.getTenantId(request);
        var colaboradores = service.findAll(tenantId);
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity<List<DadosColaborador>> findByTurmaId(@PathVariable Long id, HttpServletRequest request) {
        Long tenantId = Util.getTenantId(request);
        var colaboradores = service.findByTurmaId(tenantId, id);
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosColaborador> findById(@PathVariable Long id, HttpServletRequest request) {
        Long tenantId = Util.getTenantId(request);
        return ResponseEntity.ok(service.findById(id, tenantId));
    }

    @PostMapping
    public ResponseEntity<DadosColaborador> create(@Valid @RequestBody DadosColaboradorCreate dados,
                                                                          HttpServletRequest request){
        Long tenantId = Util.getTenantId(request);
        DadosColaborador newColaborador = service.create(tenantId, dados);
        URI uri = Util.getURI("/apis/v1/colaboradores/{id}", newColaborador.id());
        return ResponseEntity.created(uri).body(newColaborador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request){
        Long tenantId = Util.getTenantId(request);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DadosColaborador> update(@Valid @RequestBody DadosColaboradorUpdate dados,
                                                                          HttpServletRequest request){
        Long tenantId = Util.getTenantId(request);
        DadosColaborador colaborador = service.update(tenantId, dados);
        return ResponseEntity.ok(colaborador);
    }

}
