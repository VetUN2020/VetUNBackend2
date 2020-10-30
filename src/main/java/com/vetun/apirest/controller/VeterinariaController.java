package com.vetun.apirest.controller;

import com.vetun.apirest.model.Medico;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.model.Veterinaria;
import com.vetun.apirest.pojo.PerfilVeterinariaPOJO;
import com.vetun.apirest.pojo.RegistrarVeterinariaPOJO;
import com.vetun.apirest.repository.MedicoRepository;
import com.vetun.apirest.repository.UsuarioRepository;
import com.vetun.apirest.service.MedicoService;
import com.vetun.apirest.service.UsuarioService;
import com.vetun.apirest.service.VeterinariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VeterinariaController {

    private VeterinariaService veterinariaService;
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;
    private MedicoService medicoService;
    private MedicoRepository medicoRepository;

    public VeterinariaController(VeterinariaService veterinariaService, UsuarioService usuarioService, UsuarioRepository usuarioRepository, MedicoService medicoService, MedicoRepository medicoRepository) {
        this.veterinariaService = veterinariaService;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.medicoService = medicoService;
        this.medicoRepository = medicoRepository;
    }

    @GetMapping("/veterinarias")
    public ResponseEntity<?> buscarVeterinarias(){
        List<Veterinaria> veterinarias = veterinariaService.findAll();
        return ResponseEntity.ok(veterinarias);
    }

    @PostMapping("usuario/registro-veterinaria")
    public ResponseEntity<?> addVeterinaria(@RequestBody RegistrarVeterinariaPOJO registrarVeterinaria) {
        Veterinaria veterinaria = veterinariaService.mapperVeterinariaEntity(registrarVeterinaria);
        veterinariaService.save(veterinaria);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("medico/actualizar-medico")
    public ResponseEntity<Medico> actualizarMedico(@RequestBody RegistrarVeterinariaPOJO registrarVeterinaria){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario user = usuarioRepository.findByUsername(username);
        Medico medico = medicoRepository.findByUsuarioIdUsuario(user.getIdUsuario());

        System.out.println(registrarVeterinaria);

        String nombreVeterinaria = registrarVeterinaria.getNombreVeterinaria();
        Veterinaria veterinaria = veterinariaService.findByNombreVeterinaria(nombreVeterinaria);
        medico.setIdVeterinaria(veterinaria);

        final Medico medicoActualizado = medicoRepository.save(medico);
        return ResponseEntity.ok(medicoActualizado);
    }

    @GetMapping("/veterinarias/{veterinariaId}")
    public ResponseEntity<?> getVeterinaria(@PathVariable int veterinariaId){
        Veterinaria veterinaria = veterinariaService.findById(veterinariaId);

        if(veterinaria == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(veterinaria);
    }

    @GetMapping(value = {"/medico/perfil-veterinaria"})
    public ResponseEntity<?> getVeterinariaPerfil(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario user = usuarioService.findByUsername(username);
        Medico medico = medicoService.findByUsuarioIdUsuario(user.getIdUsuario());
        Veterinaria veterinaria = medico.getIdVeterinaria();

        PerfilVeterinariaPOJO perfilVeterinaria = new PerfilVeterinariaPOJO();

        perfilVeterinaria.setNombreVeterinaria(veterinaria.getNombreVeterinaria());
        perfilVeterinaria.setDireccionVeterinaria(veterinaria.getDireccionVeterinaria());
        perfilVeterinaria.setTelefonoVeterinaria(veterinaria.getTelefonoVeterinaria());
        perfilVeterinaria.setTipoVeterinaria(veterinaria.getTipoVeterinaria());


        return ResponseEntity.ok(perfilVeterinaria);
    }


}
