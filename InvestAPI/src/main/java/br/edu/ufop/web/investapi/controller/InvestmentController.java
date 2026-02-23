package br.edu.ufop.web.investapi.controller;

import br.edu.ufop.web.investapi.dto.*;
import br.edu.ufop.web.investapi.model.InvestmentType;
import br.edu.ufop.web.investapi.service.InvestmentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investments")
public class InvestmentController {

    private final InvestmentService service;

    public InvestmentController(InvestmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvestmentResponseDTO> create(@RequestBody InvestmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<InvestmentResponseDTO>> findAll(
            @RequestParam(required = false) InvestmentType type) {
        return ResponseEntity.ok(service.findAll(type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody InvestmentRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<SummaryDTO> summary() {
        return ResponseEntity.ok(service.getSummary());
    }
}
