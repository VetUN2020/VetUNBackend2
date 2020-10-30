package com.vetun.apirest.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

    private static final String[] publicResources = new String[]{ "/registro/**", "/veterinarias", "/pruebas/**" };
    private static final String[] userResources = new String[]{ "/usuario/**"};
    static final String[] medicoResources = new String[]{ "/medico/**" };
    private static final String[] duenoResources = new String[]{ "/dueno/**" };

    @Override
    public void configure( HttpSecurity httpSecurity ) throws Exception{
        httpSecurity
                .authorizeRequests( )
                .antMatchers( publicResources ).permitAll( )
                .antMatchers( userResources ).authenticated( )
                .antMatchers( medicoResources ).hasAuthority( "ROLE_MEDICO" )
                .antMatchers( duenoResources ).hasAuthority( "ROLE_DUENO" )
                .and( ).cors( ).disable( );
    }

}