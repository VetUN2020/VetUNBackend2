package com.vetun.apirest.auth.model;

import com.vetun.apirest.model.Rol;
import com.vetun.apirest.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails{

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(Usuario usuario ){
        this.username = usuario.getUsername();
        this.password = usuario.getPassword( );
        this.authorities = translateRoles( usuario.getRol() );
    }

    @Override
    public String getUsername( ){
        return username;
    }

    @Override
    public String getPassword( ){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities( ){
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired( ){
        return true;
    }

    @Override
    public boolean isAccountNonLocked( ){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired( ){
        return true;
    }

    @Override
    public boolean isEnabled( ){
        return true;
    }

    private Collection<? extends GrantedAuthority> translateRoles( Rol rol ){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>( );
            String roleName = "ROLE_" + rol.getNombreRol().toUpperCase( );
            grantedAuthorities.add( new SimpleGrantedAuthority( roleName ) );
        return grantedAuthorities;
    }
}
