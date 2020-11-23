package com.vetun.apirest.controller;

import com.vetun.apirest.model.*;
import com.vetun.apirest.pojo.PerfilDuenoPOJO;
import com.vetun.apirest.pojo.PerfilMedicoPOJO;
import com.vetun.apirest.pojo.RegistrarMedicoPOJO;
import com.vetun.apirest.repository.HoraAtencionRepository;
import com.vetun.apirest.service.*;
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
    private CostoService costoService;
    private CitaService citaService;
    private ComentarioMedicoService comentarioMedicoService;

    public MedicoController(MedicoService medicoService, UsuarioService usuarioService, RolService rolService, HoraAtencionService horaAtencionService, PasswordEncoder passwordEncoder, CostoService costoService, CitaService citaService, ComentarioMedicoService comentarioMedicoService) {
        this.medicoService = medicoService;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.horaAtencionService = horaAtencionService;
        this.passwordEncoder = passwordEncoder;
        this.costoService = costoService;
        this.citaService = citaService;
        this.comentarioMedicoService = comentarioMedicoService;
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

    @GetMapping(value = "/medicos")
    public ResponseEntity<?> buscarMedico(){
        List<Medico> medicos = medicoService.findAll();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping(value = "/medicoIdVet/{idVeterniaria}")
    public ResponseEntity<?> buscarMedicoSegunIdVeterinaria(@PathVariable("idVeterniaria") int id){
        List<Medico> medicos = medicoService.findByIdVeterinaria(id);
        if(medicos == null) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return ResponseEntity.ok(medicos);
    }

    @PostMapping(value = "/medico/agregarPrecios")
    public ResponseEntity<?> agregarPreciosCitas(@RequestBody List<Costo> costosCitas){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario user = usuarioService.findByUsername(username);
        Medico medico = medicoService.findByUsuarioIdUsuario(user.getIdUsuario());

        for(Costo costo : costosCitas){
            costo.setIdMedico(medico);
            costoService.save(costo);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = {"/medico/misCitas"})
    public ResponseEntity<?> getMisCitas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = usuarioService.findByUsername(username);
        Medico medico = medicoService.findByUsuarioIdUsuario(user.getIdUsuario());

        List<Cita> misCitas = citaService.findMisCitas(medico.getIdMedico());

        return ResponseEntity.ok(misCitas);
    }

    @GetMapping(value = {"/medico/{medicoId}/comentarios"})
    public ResponseEntity<?> getComentarios(@PathVariable int medicoId) {
        List<ComentarioMedico> comentariosMedico = comentarioMedicoService.findComentariosMedico(medicoId);

        return ResponseEntity.ok(comentariosMedico);
    }

}
