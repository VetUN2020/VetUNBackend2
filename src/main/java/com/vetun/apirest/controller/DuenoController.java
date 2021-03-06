package com.vetun.apirest.controller;

import com.vetun.apirest.email.EmailBody;
import com.vetun.apirest.email.EmailPort;
import com.vetun.apirest.model.*;
import com.vetun.apirest.pojo.PerfilDuenoPOJO;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DuenoController {
    @Autowired
    private DuenoService duenoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CitaService citaService;
    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private EmailPort emailPort;

    @PostMapping(value = {"/registro/nuevo-dueno/"})
    public ResponseEntity<Object> registerNewUser(@RequestBody RegistrarDuenoPOJO duenoPOJO) {
        Rol rol = rolService.findById(1);
        duenoPOJO.setPassword(passwordEncoder.encode(duenoPOJO.getPassword()));
        Dueno existingDueno = duenoService.findByCedulaDueno(duenoPOJO.getCedulaDueno());
        String email = duenoPOJO.getCorreoElectronico();
        Usuario usuarioExistente = usuarioService.findByCorreoElectronico(email);
        if (rol == null || existingDueno != null || !duenoService.isRightDueno(duenoPOJO) || usuarioExistente != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Dueno newDueno = duenoService.mapperDuenoEntity(duenoPOJO);
        Usuario newUsuario = usuarioService.mapperUsuarioEntityDueno(duenoPOJO);
        newUsuario.setRol(rol);

        newUsuario.setDueno(newDueno);
        newDueno.setUsuario(newUsuario);
        usuarioService.save(newUsuario);

        EmailBody emailBody = new EmailBody();

        emailBody.setEmail(email);
        emailBody.setSubject("Bienvenido a VetUN");
        emailBody.setContent("<center>" +
                "      <h2 style=\"font-family: Arial\"><b>Bienvenido a VetUN</b></h2>" +
                "      <p style=\"font-family: Arial\">Hola, " + newUsuario.getUsername() + ":</p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Estamos felices de que te unas a nuestro equipo." +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Con la ayuda de nuestro equipo tus animalitos gozaran de buena salud" +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Gracias por hacer parte de nuestra familia" +
                "      </p>" +
                "    </center>");
        emailPort.sendEmail(emailBody);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = {"/dueno/mascotas"})
    public ResponseEntity<Object> getMascotas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = duenoService.findByUsuarioIdUsuario(user.getIdUsuario());

        if (dueno == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Mascota> mascotasDueno = dueno.getMascotas();
        return ResponseEntity.ok(mascotasDueno);
    }

    @GetMapping(value = {"pruebas/{duenoId}"})
    public ResponseEntity<Object> getDuenoById(@PathVariable int duenoId) {
        Dueno dueno = duenoService.findById(duenoId);
        return ResponseEntity.ok(dueno);
    }

    @GetMapping(value = {"/dueno/perfil"})
    public ResponseEntity<Object> getDuenoPerfil() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = duenoService.findByUsuarioIdUsuario(user.getIdUsuario());

        PerfilDuenoPOJO perfil = new PerfilDuenoPOJO();

        perfil.setIdDueno(dueno.getIdDueno());
        perfil.setCedulaDueno(dueno.getCedulaDueno());
        perfil.setNombreDueno(dueno.getNombreDueno());
        perfil.setApellidoDueno(dueno.getApellidoDueno());
        perfil.setDireccionDueno(dueno.getDireccionDueno());
        perfil.setTelefonoDueno(dueno.getTelefonoDueno());
        perfil.setCorreoDueno(user.getCorreoElectronico());
        perfil.setUsuarioDueno(user.getUsername());

        return ResponseEntity.ok(perfil);
    }

    @GetMapping(value = {"/dueno/misCitas"})
    public ResponseEntity<Object> getMisCitas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = duenoService.findByUsuarioIdUsuario(user.getIdUsuario());

        List<Mascota> mascotas = mascotaService.findByDueno(dueno.getIdDueno());

        List<Cita> citas = new ArrayList<>();
        for(int i = 0; i < mascotas.size(); i++){
            List<Cita> temp = citaService.findMisCitasMascota(mascotas.get(i).getIdMascota());
            citas.addAll(temp);
        }

        return ResponseEntity.ok(citas);
    }

}
