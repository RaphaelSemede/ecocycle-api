package com.ecocycle.service;

import com.ecocycle.dto.ResiduoDTO;
import com.ecocycle.dto.ResiduoResponseDTO;
import com.ecocycle.model.Residuo;
import com.ecocycle.model.Usuario;
import com.ecocycle.repository.ResiduoRepository;
import com.ecocycle.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResiduoService {

    private final ResiduoRepository residuoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public ResiduoResponseDTO salvar(ResiduoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.usuarioId()));

        Residuo residuo = new Residuo();
        residuo.setTipo(dto.tipo());
        residuo.setPeso(dto.peso());
        residuo.setStatusColeta(dto.statusColeta() != null ? dto.statusColeta() : "PENDENTE");
        residuo.setDataRegistro(LocalDateTime.now());
        residuo.setUsuario(usuario);

        Residuo residuoSalvo = residuoRepository.save(residuo);
        return new ResiduoResponseDTO(residuoSalvo);
    }

    @Transactional(readOnly = true)
    public List<ResiduoResponseDTO> listarTodos() {
        return residuoRepository.findAll()
                .stream()
                .map(ResiduoResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResiduoResponseDTO buscarPorId(Long id) {
        Residuo residuo = residuoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado com ID: " + id));
        return new ResiduoResponseDTO(residuo);
    }

    @Transactional
    public void deletar(Long id) {
        if (!residuoRepository.existsById(id)) {
            throw new RuntimeException("Resíduo não encontrado com ID: " + id);
        }
        residuoRepository.deleteById(id);
    }

    public ResiduoResponseDTO atualizar(Long id, ResiduoDTO dto) {
        Residuo residuo = residuoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado com ID: " + id));

        residuo.setTipo(dto.tipo());
        residuo.setPeso(dto.peso());
        if (dto.statusColeta() != null) {
            residuo.setStatusColeta(dto.statusColeta());
        }

        Residuo residuoAtualizado = residuoRepository.save(residuo);
        return new ResiduoResponseDTO(residuoAtualizado);
    }
}

