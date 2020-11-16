package com.vetun.apirest.controller;

import com.vetun.apirest.model.*;
import com.vetun.apirest.pojo.FechaCitaPOJO;
import com.vetun.apirest.pojo.MenuBarUserPOJO;
import com.vetun.apirest.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.*;

@RestController
public class UsuarioController {
    private UsuarioService usuarioService;
    private DuenoService duenoService;
    private MedicoService medicoService;
    private HoraAtencionService horaAtencionService;
    private CitaService citaService;
    private CostoService costoService;

    public UsuarioController(UsuarioService usuarioService, DuenoService duenoService, MedicoService medicoService, HoraAtencionService horaAtencionService, CitaService citaService, CostoService costoService) {
        this.usuarioService = usuarioService;
        this.duenoService = duenoService;
        this.medicoService = medicoService;
        this.horaAtencionService = horaAtencionService;
        this.citaService = citaService;
        this.costoService = costoService;
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

    @PostMapping(value = {"/usuario/horasDisponibles"})
    public ResponseEntity<?> obtenerHorasDisponibles(@RequestBody FechaCitaPOJO fechaCita){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );

        Medico medico = medicoService.findById(fechaCita.getIdMedico());
        List<HoraAtencion> horarios = horaAtencionService.findByMedico(medico);
        List<Cita> citas = citaService.findCitas(fechaCita.getFechaCita(), fechaCita.getIdMedico());


        List<HoraAtencion> horasRemover = new ArrayList<>() ;

        for(Cita c: citas){
         for(HoraAtencion h : horarios){
             if(c.getHoraCita().equals(h.getHoraTiempo())){
                 horasRemover.add(h);
             }
         }
        }
        horarios.removeAll(horasRemover);

        return ResponseEntity.ok(horarios);
    }

    @GetMapping(value = {"usuario/costos/{medicoId}"})
    public ResponseEntity<?> obtenerCostos(@PathVariable int medicoId){

        Medico medico = medicoService.findById(medicoId);

        List<Costo> costos = costoService.findByIdMedico(medico);

        if(costos == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return  ResponseEntity.ok(costos);

    }

    @PostMapping(value = {"/usuario/agendarCita"})
    public ResponseEntity<?> agendarCita(@RequestBody Cita agendarCita){
        Cita cita = agendarCita;
        citaService.save(cita);
        return ResponseEntity.ok(cita);
    }


}
