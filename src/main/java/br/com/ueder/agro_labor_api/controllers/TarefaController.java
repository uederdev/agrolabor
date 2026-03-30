package br.com.ueder.agro_labor_api.controllers;

import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefa;
import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefaCreate;
import br.com.ueder.agro_labor_api.dtos.tarefa.DadosTarefaUpdate;
import br.com.ueder.agro_labor_api.services.TarefaService;
import br.com.ueder.agro_labor_api.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosTarefa>> findAll(HttpServletRequest request){
        Long tenantId = Util.getTenantId(request);
        List<DadosTarefa> tarefas = service.findAll(tenantId);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosTarefa> findById(HttpServletRequest request, @PathVariable Long id){
        Long tenantId = Util.getTenantId(request);
        DadosTarefa dadosTarefa = service.findById(tenantId, id);
        return ResponseEntity.ok(dadosTarefa);
    }

    @PostMapping
    public ResponseEntity<DadosTarefa> create(@Valid @RequestBody DadosTarefaCreate dados, HttpServletRequest request){
        Long tenantId = Util.getTenantId(request);
        DadosTarefa newTarefa = service.create(dados, tenantId);
        URI uri = Util.getURI("/apis/v1/tarefas/{id}", newTarefa.id());
        return ResponseEntity.created(uri).body(newTarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request){
        Long tenantId = Util.getTenantId(request);
        service.delete(tenantId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DadosTarefa> update(@Valid @RequestBody DadosTarefaUpdate dados, HttpServletRequest request){
        Long tenantId = Util.getTenantId(request);
        DadosTarefa tarefaUpdate = service.update(tenantId, dados);
        return ResponseEntity.ok(tarefaUpdate);
    }


}
