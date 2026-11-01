package com.ecocycle.controller;

import com.ecocycle.dto.PontosColetaDTO;
import com.ecocycle.dto.PontosColetaResponseDTO;
import com.ecocycle.service.PontosColetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pontos-coleta")
@RequiredArgsConstructor
public class PontosColetaController {

    private final PontosColetaService pontoColetaService;

    @PostMapping
    public ResponseEntity<PontosColetaResponseDTO> criar(@RequestBody @Valid PontosColetaDTO dto) {
        PontosColetaResponseDTO novoPonto = pontoColetaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPonto);
    }

    @GetMapping
    public ResponseEntity<List<PontosColetaResponseDTO>> listarTodos() {
        List<PontosColetaResponseDTO> pontos = pontoColetaService.listarTodos();
        return ResponseEntity.ok(pontos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontosColetaResponseDTO> buscaPorId(@PathVariable Long id) {
        PontosColetaResponseDTO dto = pontoColetaService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PontosColetaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PontosColetaDTO dto) {
        PontosColetaResponseDTO pontosSalvo = pontoColetaService.atualizar(id, dto);
        return ResponseEntity.ok(pontosSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pontoColetaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}