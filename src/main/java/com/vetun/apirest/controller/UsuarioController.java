package com.vetun.apirest.controller;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.model.HoraAtencion;
import com.vetun.apirest.model.Medico;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.MenuBarUserPOJO;
import com.vetun.apirest.service.DuenoService;
import com.vetun.apirest.service.HoraAtencionService;
import com.vetun.apirest.service.MedicoService;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {
    private UsuarioService usuarioService;
    private DuenoService duenoService;
    private MedicoService medicoService;
    private HoraAtencionService horaAtencionService;

    public UsuarioController(UsuarioService usuarioService, DuenoService duenoService, MedicoService medicoService,HoraAtencionService horaAtencionService) {
        this.usuarioService = usuarioService;
        this.duenoService = duenoService;
        this.medicoService = medicoService;
        this.horaAtencionService = horaAtencionService;
    }

    @GetMapping(value = {"/usuario"} )
    public ResponseEntity<?> getUserAuthenticated(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario userAuth = usuarioService.findByUsername(username);
        String rol= userAuth.getRol().getNombreRol();
        MenuBarUserPOJO user = new MenuBarUserPOJO();
        user.setRolUsuario(rol);

        if(rol.equals("DUENO")){
            Dueno dueno = duenoService.findByUsuarioIdUsuario(userAuth.getIdUsuario());
            user.setNombreUsuario(dueno.getNombreDueno());
            user.setApellidoUsuario(dueno.getApellidoDueno());
        }else{
            Medico medico = medicoService.findByUsuarioIdUsuario(userAuth.getIdUsuario());
            user.setNombreUsuario(medico.getNombreMedico());
            user.setApellidoUsuario(medico.getApellidoMedico());
        }
        
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = {"/pruebas/horasDisponibles"})
    public ResponseEntity<?> obtenerHorasDisponibles(){

        Medico medico = medicoService.findById(1);
        List<HoraAtencion> horarios = horaAtencionService.findByMedico(medico);

        return ResponseEntity.ok(horarios);
    }

}
