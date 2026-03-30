package br.com.ueder.agro_labor_api.controllers;

import br.com.ueder.agro_labor_api.dtos.proprietario.DadosProprietario;
import br.com.ueder.agro_labor_api.dtos.proprietario.DadosProprietarioCreate;
import br.com.ueder.agro_labor_api.services.ProprietarioService;
import br.com.ueder.agro_labor_api.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/proprietarios")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @GetMapping
    public ResponseEntity<List<DadosProprietario>> findAll(HttpServletRequest req){
        Long tenantId = Util.getTenantId(req);
        List<DadosProprietario> proprietarios = proprietarioService.findAll(tenantId);
        return ResponseEntity.ok(proprietarios);
    }

    @PostMapping
    public ResponseEntity<DadosProprietario> create(@Valid @RequestBody DadosProprietarioCreate dados,
                                                    HttpServletRequest req){
        Long tenantId = Util.getTenantId(req);
        DadosProprietario newProprietario = proprietarioService.create(tenantId, dados);
        URI uri = Util.getURI("/apis/v1/proprietarios/{id}", newProprietario.id());
        return ResponseEntity.created(uri).body(newProprietario);
    }
}
