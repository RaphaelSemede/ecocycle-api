package com.ecocycle.controller;

import com.ecocycle.dto.UsuarioDTO;
import com.ecocycle.dto.UsuarioResponseDTO;
import com.ecocycle.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscaPorId(@PathVariable Long id) {
        UsuarioResponseDTO dto = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        UsuarioResponseDTO usuarioSalvo = usuarioService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO dto) {
        UsuarioResponseDTO usuarioSalvo = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}