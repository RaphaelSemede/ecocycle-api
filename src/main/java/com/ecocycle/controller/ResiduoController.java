package com.ecocycle.controller;

import com.ecocycle.dto.ResiduoDTO;
import com.ecocycle.dto.ResiduoResponseDTO;
import com.ecocycle.service.ResiduoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residuos")
@RequiredArgsConstructor
public class ResiduoController {

    private final ResiduoService residuoService;

    @GetMapping
    public ResponseEntity<List<ResiduoResponseDTO>> listarTodos() {
        List<ResiduoResponseDTO> residuos = residuoService.listarTodos();
        return ResponseEntity.ok(residuos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResiduoResponseDTO> buscarPorId(@PathVariable Long id) {
        ResiduoResponseDTO dto = residuoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ResiduoResponseDTO> cadastrar(@RequestBody @Valid ResiduoDTO dto) {
        ResiduoResponseDTO residuoSalvo = residuoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(residuoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResiduoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ResiduoDTO dto) {
        ResiduoResponseDTO residuoAtualizado = residuoService.atualizar(id,dto);
        return ResponseEntity.ok(residuoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        residuoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}