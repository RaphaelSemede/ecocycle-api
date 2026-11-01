package com.ecocycle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PontosColetaDTO(
        @NotBlank(message = "O endereço é obrigatório")
        String endereco,

        @NotNull(message = "A capacidade máxima é obrigatória")
        @Positive(message = "A capacidade máxima deve ser um valor positivo")
        BigDecimal capacidadeMaxima,

        BigDecimal quantidadeAtual
) {
}
