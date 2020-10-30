package com.vetun.apirest.auth.service;

import com.vetun.apirest.auth.model.UserDetailsImpl;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service( "userDetailsService" )
public class UserDetailsServiceImpl implements UserDetailsService{

    private UsuarioService usuarioService;

    public UserDetailsServiceImpl(UsuarioService usuarioService ){
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername( String username ){
        Usuario usuario = usuarioService.findByUsername( username );
        if( usuario == null ){
            throw new UsernameNotFoundException( "" );
        }
        return new UserDetailsImpl( usuario );
    }


}