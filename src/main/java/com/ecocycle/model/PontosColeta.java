package com.ecocycle.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PONTOS_COLETA")
public class PontosColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String endereco;

    @Column(name = "CAPACIDADE_MAXIMA", nullable = false)
    private BigDecimal capacidadeMaxima;

    @Column(name = "QUANTIDADE_ATUAL")
    private BigDecimal quantidadeAtual = BigDecimal.ZERO;
}