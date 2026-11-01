package com.ecocycle.service;

import com.ecocycle.dto.UsuarioDTO;
import com.ecocycle.dto.UsuarioResponseDTO;
import com.ecocycle.exception.EmailDuplicadoException;
import com.ecocycle.model.Usuario;
import com.ecocycle.repository.UsuarioRepository;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDTO salvar(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new EmailDuplicadoException("E-mail já cadastrado no sistema: " + dto.email());
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setRole(dto.role() != null ? dto.role() : "USER");

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        return new UsuarioResponseDTO(usuario);
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenha(dto.senha());
        }

        if (dto.role() != null) {
            usuario.setRole(dto.role());
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioAtualizado);
    }
}