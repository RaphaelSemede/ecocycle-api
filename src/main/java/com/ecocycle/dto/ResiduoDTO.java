package com.ecocycle.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ResiduoDTO(
        @NotBlank(message = "O tipo do resíduo é obrigatório")
        String tipo,

        @NotNull(message = "O peso é obrigatório")
        @Positive(message = "O peso deve ser um valor positivo")
        BigDecimal peso,

        String statusColeta,
        
        Long usuarioId
) {
}
