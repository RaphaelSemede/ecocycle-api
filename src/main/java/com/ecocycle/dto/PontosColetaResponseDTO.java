package com.ecocycle.dto;

import com.ecocycle.model.PontosColeta; // Verifique se o import bate com o nome da sua Entity
import java.math.BigDecimal;

public record PontosColetaResponseDTO(
        Long id,
        String endereco,
        BigDecimal capacidadeMaxima,
        BigDecimal quantidadeAtual
) {
    // Construtor alinhado com a classe PontosColeta
    public PontosColetaResponseDTO(PontosColeta pontoColeta) {
        this(
                pontoColeta.getId(),
                pontoColeta.getEndereco(),
                pontoColeta.getCapacidadeMaxima(),
                pontoColeta.getQuantidadeAtual()
        );
    }
}