package com.ecocycle.service;

import com.ecocycle.dto.PontosColetaDTO;
import com.ecocycle.dto.PontosColetaResponseDTO;
import com.ecocycle.model.PontosColeta;
import com.ecocycle.repository.PontosColetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PontosColetaService {

    private final PontosColetaRepository pontosColetaRepository;

    @Transactional
    public PontosColetaResponseDTO salvar(PontosColetaDTO dto) {
        PontosColeta pontosColeta = new PontosColeta();
        pontosColeta.setEndereco(dto.endereco());
        pontosColeta.setCapacidadeMaxima(dto.capacidadeMaxima());
        pontosColeta.setQuantidadeAtual(dto.quantidadeAtual() != null ? dto.quantidadeAtual() : BigDecimal.ZERO);

        PontosColeta salvo = pontosColetaRepository.save(pontosColeta);
        return new PontosColetaResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<PontosColetaResponseDTO> listarTodos() {
        return pontosColetaRepository.findAll()
                .stream()
                .map(PontosColetaResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PontosColetaResponseDTO buscarPorId(Long id) {
        PontosColeta pontosColeta = pontosColetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        return new PontosColetaResponseDTO(pontosColeta);
    }

    @Transactional
    public PontosColetaResponseDTO atualizar(Long id, PontosColetaDTO dto) {
        PontosColeta pontosColeta = pontosColetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado com ID: " + id));

        pontosColeta.setEndereco(dto.endereco());
        pontosColeta.setCapacidadeMaxima(dto.capacidadeMaxima());
        pontosColeta.setQuantidadeAtual(dto.quantidadeAtual() != null ? dto.quantidadeAtual() : BigDecimal.ZERO);

        PontosColeta pontosAtualizado = pontosColetaRepository.save(pontosColeta);
        return new PontosColetaResponseDTO(pontosAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!pontosColetaRepository.existsById(id)) {
            throw new RuntimeException("Ponto de coleta não encontrado com ID: " + id);
        }
        pontosColetaRepository.deleteById(id);
    }

}