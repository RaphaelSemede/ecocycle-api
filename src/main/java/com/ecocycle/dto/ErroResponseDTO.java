package com.ecocycle.dto;

import java.time.LocalDateTime;

public record ErroResponseDTO(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String mensagem,
        String path
) {}