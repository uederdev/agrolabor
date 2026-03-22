package br.com.ueder.agro_labor_api.controllers;

import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresa;
import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresaCreate;
import br.com.ueder.agro_labor_api.dtos.empresa.DadosEmpresaUpdate;
import br.com.ueder.agro_labor_api.services.EmpresaService;
import br.com.ueder.agro_labor_api.utils.Util;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/empresas")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DadosEmpresa>> findAll(){
        var empresas = service.findAll();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosEmpresa> findById(@PathVariable Long id){
        DadosEmpresa empresa = service.findById(id);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping
    public ResponseEntity<DadosEmpresa> create(@Valid @RequestBody DadosEmpresaCreate dados){
        var newEmpresa = service.create(dados);
        URI uri = Util.getURI("/apis/v1/empresas/{id}",newEmpresa.id());
        return ResponseEntity.created(uri).body(newEmpresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DadosEmpresa> update(@Valid @RequestBody DadosEmpresaUpdate dados){
        var newEmpresa = service.update(dados);
        return ResponseEntity.ok(newEmpresa);
    }
}
