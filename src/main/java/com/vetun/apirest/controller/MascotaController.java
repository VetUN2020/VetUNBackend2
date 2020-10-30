package com.vetun.apirest.controller;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.model.Mascota;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.RegistrarMascotaPOJO;
import com.vetun.apirest.service.MascotaService;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MascotaController {

    private MascotaService mascotaService;
    private UsuarioService usuarioService;

    public MascotaController(MascotaService mascotaService, UsuarioService usuarioService) {
        this.mascotaService = mascotaService;
        this.usuarioService = usuarioService;
    }

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
    public ResponseEntity<?> obtenerMascotas(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = user.getDueno();

        List<Mascota> mascotas = mascotaService.findByDueno(dueno.getIdDueno());

        if(mascotas == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return  ResponseEntity.ok(mascotas);

    }


}
