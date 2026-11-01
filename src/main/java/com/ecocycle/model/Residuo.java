package com.ecocycle.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "residuos")
public class Residuo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private BigDecimal peso;

    @Column(name = "DATA_REGISTRO")
    private LocalDateTime dataRegistro = LocalDateTime.now();

    @Column(name = "STATUS_COLETA")
    private String statusColeta = "PENDENTE";

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;
}