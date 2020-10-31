package com.vetun.apirest.controller;

import com.vetun.apirest.model.*;
import com.vetun.apirest.pojo.PerfilDuenoPOJO;
import com.vetun.apirest.pojo.PerfilMedicoPOJO;
import com.vetun.apirest.pojo.RegistrarMedicoPOJO;
import com.vetun.apirest.repository.HoraAtencionRepository;
import com.vetun.apirest.service.HoraAtencionService;
import com.vetun.apirest.service.MedicoService;
import com.vetun.apirest.service.RolService;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicoController {

    private MedicoService medicoService;
    private UsuarioService usuarioService;
    private RolService rolService;
    private HoraAtencionService horaAtencionService;
    private PasswordEncoder passwordEncoder;


    public MedicoController(MedicoService medicoService, UsuarioService usuarioService, RolService rolService, PasswordEncoder passwordEncoder, HoraAtencionService horaAtencionService) {
        this.medicoService = medicoService;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
        this.horaAtencionService = horaAtencionService;
    }

    @GetMapping("/medicos/{medicoId}")
    public ResponseEntity<?>getMedico(@PathVariable int medicoId){
        Medico medico = medicoService.findById(medicoId);
        if(medico == null) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return ResponseEntity.ok(medico);
    }

    @PostMapping( value = { "/registro/nuevo-medico/" } )
    public ResponseEntity<Void> registrarNuevoMedico(@RequestBody RegistrarMedicoPOJO medicoPOJO){
        Rol rol = rolService.findById( 2 );
        medicoPOJO.setPassword(passwordEncoder.encode(medicoPOJO.getPassword()));
        Medico medicoExistente = medicoService.findByCedulaMedico( medicoPOJO.getCedulaMedico() );
        String email = medicoPOJO.getCorreoElectronico();
        Usuario usuarioExistente = usuarioService.findByCorreoElectronico(email);
        if( rol == null || medicoExistente != null || !medicoService.isRightMedico( medicoPOJO ) || usuarioExistente != null ){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

        Medico nuevoMedico = medicoService.mapperMedicoEntity(medicoPOJO);
        Usuario newUsuario = usuarioService.mapperUsuarioEntityMedico(medicoPOJO);
        newUsuario.setRol(rol);

        newUsuario.setMedico(nuevoMedico);
        nuevoMedico.setUsuario(newUsuario);
        usuarioService.save(newUsuario);

        return new ResponseEntity<>( HttpStatus.CREATED );
    }
    @GetMapping(value = {"/medico/perfil"})
    public ResponseEntity<?> getMedicoPerfil(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario user = usuarioService.findByUsername(username);
        Medico medico = medicoService.findByUsuarioIdUsuario(user.getIdUsuario());

        PerfilMedicoPOJO perfil = new PerfilMedicoPOJO();

        perfil.setCedulaMedico(medico.getCedulaMedico());
        perfil.setNombreMedico(medico.getNombreMedico());
        perfil.setApellidoMedico(medico.getApellidoMedico());
        perfil.setDireccionMedico(medico.getDireccionMedico());
        perfil.setTelefonoMedico(medico.getTelefonoMedico());
        perfil.setCorreoMedico(user.getCorreoElectronico());
        perfil.setUsuarioMedico(user.getUsername());
        perfil.setMatriculaProfesional(medico.getMatriculaProfesional());

        return ResponseEntity.ok(perfil);
    }

    @PutMapping(value = {"/medico/agregarHora"})
    public ResponseEntity<?> agregarHorasDisponibles(@RequestBody List<HoraAtencion> horaAtencions){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario user = usuarioService.findByUsername(username);
        Medico medico = medicoService.findByUsuarioIdUsuario(user.getIdUsuario());

        for(HoraAtencion hora : horaAtencions){
            hora.setIdMedico(medico);
            horaAtencionService.save(hora);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/medicos")
    public ResponseEntity<?> buscarMedico(){
        List<Medico> medicos = medicoService.findAll();
        return ResponseEntity.ok(medicos);
    }

}
