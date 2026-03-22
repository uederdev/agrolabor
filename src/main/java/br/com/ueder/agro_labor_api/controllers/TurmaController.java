package br.com.ueder.agro_labor_api.controllers;

import java.net.URI;
import java.util.List;

import br.com.ueder.agro_labor_api.dtos.turma.DadosTurmaCreate;
import br.com.ueder.agro_labor_api.dtos.turma.DadosTurmaUpdate;
import br.com.ueder.agro_labor_api.entities.turma.Turma;
import br.com.ueder.agro_labor_api.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ueder.agro_labor_api.dtos.turma.DadosTurma;
import br.com.ueder.agro_labor_api.services.TurmaService;

@RestController
@RequestMapping("/apis/v1/turmas")
public class TurmaController {
	
	private final TurmaService service;
	
	public TurmaController(TurmaService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<DadosTurma>> findAll(HttpServletRequest request) {
		Long tenantId = Util.getTenantId(request);
		List<DadosTurma> turmas = service.findAll(tenantId);
		return ResponseEntity.ok(turmas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosTurma> findById(@PathVariable Long id, HttpServletRequest request) {
		Long tenantId = Util.getTenantId(request);
		DadosTurma turma = service.findById(tenantId, id);
		return ResponseEntity.ok(turma);
	}

	@PostMapping
	public ResponseEntity<DadosTurma> create(@Valid @RequestBody DadosTurmaCreate dados, HttpServletRequest request) {
		Long tenantId = Util.getTenantId(request);
		DadosTurma turmasNew = service.create(dados, tenantId);
		URI uri = Util.getURI("/apis/v1/turmas/{id}", turmasNew.id());
		return ResponseEntity.created(uri).body(turmasNew);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {
		Long tenantId = Util.getTenantId(request);
		service.delete(tenantId, id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<DadosTurma> update(@Valid @RequestBody DadosTurmaUpdate dados, HttpServletRequest request) {
		Long tenantId = Util.getTenantId(request);
		DadosTurma dadosEdit = service.update(tenantId, dados);
		return ResponseEntity.ok(dadosEdit);
	}

}
