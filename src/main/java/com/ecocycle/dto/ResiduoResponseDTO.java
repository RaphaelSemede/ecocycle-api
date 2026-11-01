package com.ecocycle.dto;

import com.ecocycle.model.Residuo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResiduoResponseDTO(
        Long id,
        String tipo,
        BigDecimal peso,
        LocalDateTime dataRegistro,
        String statusColeta,
        UsuarioResponseDTO usuario
) {
    public ResiduoResponseDTO(Residuo residuo) {
        this(
                residuo.getId(),
                residuo.getTipo(),
                residuo.getPeso(),
                residuo.getDataRegistro(),
                residuo.getStatusColeta(),
                residuo.getUsuario() != null ? new UsuarioResponseDTO(residuo.getUsuario()) : null
        );
    }
}
