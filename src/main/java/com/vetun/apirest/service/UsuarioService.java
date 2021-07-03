package com.vetun.apirest.service;

import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.pojo.RegistrarMedicoPOJO;
import com.vetun.apirest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByUsername(String username ){
        return usuarioRepository.findByUsername( username );
    }

    public Usuario findByCorreoElectronico( String correo ){ return usuarioRepository.findByCorreoElectronico( correo );}

    public void save( Usuario usuario ){
        usuarioRepository.save( usuario );
    }

    public Usuario mapperUsuarioEntityDueno(RegistrarDuenoPOJO duenoPOJO ){
        Usuario newUser = new Usuario();
        newUser.setUsername( duenoPOJO.getUsername() );
        newUser.setCorreoElectronico( duenoPOJO.getCorreoElectronico() );
        newUser.setPassword(duenoPOJO.getPassword());
        newUser.setActive2FA(false);
        return newUser;
    }

    public Usuario mapperUsuarioEntityMedico(RegistrarMedicoPOJO medicoPOJO){
        Usuario newUser = new Usuario( );
        newUser.setUsername( medicoPOJO.getUsername() );
        newUser.setCorreoElectronico( medicoPOJO.getCorreoElectronico() );
        newUser.setPassword(medicoPOJO.getPassword());
        newUser.setActive2FA(false);
        return newUser;
    }
}
