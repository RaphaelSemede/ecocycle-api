package com.ecocycle.exception;

import com.ecocycle.dto.ErroResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErroResponseDTO> tratarEmailDuplicado(
            EmailDuplicadoException ex,
            HttpServletRequest request
    ) {
        ErroResponseDTO erro = new ErroResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflito de Dados",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponseDTO> tratarRecursoNaoEncontrado(
            RuntimeException ex,
            HttpServletRequest request
    ) {
        ErroResponseDTO erro = new ErroResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso Não Encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Captura falhas de validação dos DTOs (@Valid / @NotBlank / @NotNull) e retorna HTTP 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> tratarErrosValidacao(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String mensagens = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErroResponseDTO erro = new ErroResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição Inválida (Validação)",
                mensagens,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}