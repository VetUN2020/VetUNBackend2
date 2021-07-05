package com.vetun.apirest.controller;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.model.Mascota;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.RegistrarMascotaPOJO;
import com.vetun.apirest.service.MascotaService;
import com.vetun.apirest.service.UsuarioService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;


@RestController
public class MascotaController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MascotaController.class);
    private static final String MS_NO_ENCONTRADAS = "Mascotas no encontradas";

    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping( value = { "dueno/registro-mascota" } )
    public ResponseEntity<Void> registrarNuevaMascota(@RequestBody RegistrarMascotaPOJO mascotaPOJO){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = user.getDueno();
        Mascota nuevaMascota = mascotaService.mapperMascotaEntity(mascotaPOJO);
        nuevaMascota.setIdDueno(dueno);
        mascotaService.save(nuevaMascota);
        return new ResponseEntity<>( HttpStatus.CREATED );
    }

    @GetMapping(value = {"dueno/mis-mascotas"})
    public ResponseEntity<Object> obtenerMascotas(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = user.getDueno();

        List<Mascota> mascotas = mascotaService.findByDueno(dueno.getIdDueno());

        if(mascotas == null){
            LOGGER.info(MS_NO_ENCONTRADAS);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return  ResponseEntity.ok(mascotas);

    }

    @GetMapping(value = {"pruebas/mascotas"})
    public ResponseEntity<Object> obtenerMascotasAll(){

        List<Mascota> mascotas = mascotaService.findAll();

        return  ResponseEntity.ok(mascotas);

    }


}
