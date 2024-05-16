package br.com.tads.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.tads.ecommerce.model.Usuario;
import br.com.tads.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean verificarExistenciaEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean verificarCredenciais(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario != null && usuario.getSenha().equals(senha);
    }

}

