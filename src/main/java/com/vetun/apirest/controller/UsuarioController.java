package com.vetun.apirest.controller;

import com.vetun.apirest.email.EmailBody;
import com.vetun.apirest.email.EmailPort;
import com.vetun.apirest.model.*;
import com.vetun.apirest.pojo.CambioContrasenaPOJO;
import com.vetun.apirest.pojo.FechaCitaPOJO;
import com.vetun.apirest.pojo.MenuBarUserPOJO;
import com.vetun.apirest.pojo.RecuperarContrasenaPOJO;
import com.vetun.apirest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
import java.util.*;

@RestController
public class UsuarioController {
    private UsuarioService usuarioService;
    private DuenoService duenoService;
    private MedicoService medicoService;
    private HoraAtencionService horaAtencionService;
    private CitaService citaService;
    private CostoService costoService;
    private PasswordResetService passwordResetService;
    private MascotaService mascotaService;

    @Autowired
    private EmailPort emailPort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, DuenoService duenoService, MedicoService medicoService, HoraAtencionService horaAtencionService, CitaService citaService, CostoService costoService, PasswordResetService passwordResetService, MascotaService mascotaService, EmailPort emailPort, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.duenoService = duenoService;
        this.medicoService = medicoService;
        this.horaAtencionService = horaAtencionService;
        this.citaService = citaService;
        this.costoService = costoService;
        this.passwordResetService = passwordResetService;
        this.mascotaService = mascotaService;
        this.emailPort = emailPort;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = {"/usuario"})
    public ResponseEntity<?> getUserAuthenticated() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario userAuth = usuarioService.findByUsername(username);
        String rol = userAuth.getRol().getNombreRol();
        MenuBarUserPOJO user = new MenuBarUserPOJO();
        user.setRolUsuario(rol);

        if (rol.equals("DUENO")) {
            Dueno dueno = duenoService.findByUsuarioIdUsuario(userAuth.getIdUsuario());
            user.setNombreUsuario(dueno.getNombreDueno());
            user.setApellidoUsuario(dueno.getApellidoDueno());
        } else {
            Medico medico = medicoService.findByUsuarioIdUsuario(userAuth.getIdUsuario());
            user.setNombreUsuario(medico.getNombreMedico());
            user.setApellidoUsuario(medico.getApellidoMedico());
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping(value = {"/usuario/horasDisponibles"})
    public ResponseEntity<?> obtenerHorasDisponibles(@RequestBody FechaCitaPOJO fechaCita) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Medico medico = medicoService.findById(fechaCita.getIdMedico());
        List<HoraAtencion> horarios = horaAtencionService.findByMedico(medico);
        List<Cita> citas = citaService.findCitas(fechaCita.getFechaCita(), fechaCita.getIdMedico());


        List<HoraAtencion> horasRemover = new ArrayList<>();

        for (Cita c : citas) {
            for (HoraAtencion h : horarios) {
                if (c.getHoraCita().equals(h.getHoraTiempo())) {
                    horasRemover.add(h);
                }
            }
        }
        horarios.removeAll(horasRemover);

        return ResponseEntity.ok(horarios);
    }

    @GetMapping(value = {"usuario/costos/{medicoId}"})
    public ResponseEntity<?> obtenerCostos(@PathVariable int medicoId) {

        Medico medico = medicoService.findById(medicoId);

        List<Costo> costos = costoService.findByIdMedico(medico);

        if (costos == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(costos);

    }

    @PostMapping(value = {"/usuario/agendarCita"})
    public ResponseEntity<?> agendarCita(@RequestBody Cita agendarCita) {
        Cita cita = agendarCita;
        citaService.save(cita);

        Medico medico = medicoService.findById(agendarCita.getIdMedico().getIdMedico());
        Mascota mascota = mascotaService.findById(agendarCita.getIdMascota().getIdMascota());
        Dueno dueno = duenoService.findById(mascota.getIdDueno().getIdDueno());
        Usuario usuarioM = usuarioService.findByUsername(medico.getUsuario().getUsername());
        Usuario usuarioD = usuarioService.findByUsername(dueno.getUsuario().getUsername());

        EmailBody emailBody = new EmailBody();

        emailBody.setEmail(usuarioM.getCorreoElectronico());
        emailBody.setSubject("Nueva cita agendada");
        emailBody.setContent("<center>" +
                "      <h2 style=\"font-family: Arial\"><b>Nueva cita agendada</b></h2>" +
                "      <p style=\"font-family: Arial\">Hola, " + usuarioM.getUsername() + "</p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Se te ha asignado una nueva cita con los siguientes datos:" +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Dueño: " + dueno.getNombreDueno() + " " + dueno.getApellidoDueno() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Mascota: " + mascota.getNombreMascota() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Fecha: " + agendarCita.getFechaCita() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Hora: " + agendarCita.getHoraCita() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Motivo: " + agendarCita.getIdAtencion().getDescripcionAtencion() +
                "      </p>" +
                "    </center>");
        emailPort.sendEmail(emailBody);

        emailBody.setEmail(usuarioD.getCorreoElectronico());
        emailBody.setSubject("Nueva cita agendada");
        emailBody.setContent("<center>" +
                "      <h2 style=\"font-family: Arial\"><b>Nueva cita agendada</b></h2>" +
                "      <p style=\"font-family: Arial\">Hola, " + usuarioD.getUsername() + "</p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Se ha agendado correctamente tu cita con los siguientes datos:" +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Medico: " + medico.getNombreMedico() + " " + medico.getApellidoMedico() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Mascota: " + mascota.getNombreMascota() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Fecha: " + agendarCita.getFechaCita() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Hora: " + agendarCita.getHoraCita() +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Motivo: " + agendarCita.getIdAtencion().getDescripcionAtencion() +
                "      </p>" +
                "    </center>");
        emailPort.sendEmail(emailBody);

        return ResponseEntity.ok(cita);
    }

    @PostMapping(value = {"/recuperarContrasena"})
    public ResponseEntity<?> enviarCorreoRecuperacion(@RequestBody RecuperarContrasenaPOJO correoElectronico) {
        Usuario user = usuarioService.findByCorreoElectronico(correoElectronico.getCorreoRecuperacion());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PasswordReset passwordExistente = passwordResetService.findByUsuario(user);

        if (passwordExistente != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        String token = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.HOUR_OF_DAY, -5);
        Date objDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(objDate.getTime());
        Time time = new Time(0);

        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setUsuario(user);
        passwordReset.setToken(token);
        passwordReset.setFechaExpiracion(date);
        passwordReset.setHoraExpiracion(time);
        passwordResetService.save(passwordReset);

        EmailBody emailBody = new EmailBody();

        emailBody.setEmail(user.getCorreoElectronico());
        emailBody.setSubject("Recuperación de contraseña");
        emailBody.setContent("<center>" +
                "      <h2 style=\"font-family: Arial\"><b>Recuperación contraseña</b></h2>" +
                "      <p style=\"font-family: Arial\">Hola, " + user.getUsername() + ":</p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Recibimos tu solicitud para restablecer la contraseña :" +
                "      </p>" +
                "      <p style=\"font-family: Arial\">" +
                "        Para restablecer tu contraseña, haz clic en el botón :" +
                "      </p>" +
                "      <button" +
                "        style=\"" +
                "          text-decoration: none;" +
                "          padding: 10px;" +
                "          color: #ffffff;" +
                "          background-color: #1abc9c;" +
                "          border-radius: 6px;" +
                "          border: 1px solid black;" +
                "          width: 100px;" +
                "        \"" +
                "      >" +
                "        <a" +
                "          href=\"http://localhost:8080/nuevaContrasenia?token=" + token + "\"" +
                "          target=\"_blank\"" +
                "          style=\"color: #ffffff; font-family: Calibri; font-size: 15px\"" +
                "          >Clic aqui</a" +
                "        >" +
                "      </button>" +
                "      <br />" +
                "      <p style=\"font-family: Arial\">" +
                "        O sigue el siguiente enlace a continuación :" +
                "      </p>" +
                "      <p>" +
                "        <a" +
                "          href=\"http://localhost:8080/nuevaContrasenia?token=" + token + "\"" +
                "          target=\"_blank\"" +
                "          style=\"color: blue; font-family: Calibri; font-size: 15px\"" +
                "          >http://localhost:8080/nuevaContrasenia?token=" + token + "</a" +
                "        >" +
                "      </p>" +
                "    </center>");
        emailPort.sendEmail(emailBody);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = {"/nuevaContrasena"})
    public ResponseEntity<?> nuevaContrasena(@RequestBody CambioContrasenaPOJO cambioContrasenaPOJO) {
        PasswordReset passwordReset = passwordResetService.findByToken(cambioContrasenaPOJO.getToken());
        Usuario user = passwordReset.getUsuario();
        user.setPassword(passwordEncoder.encode(cambioContrasenaPOJO.getNuevaContrasena()));

        passwordResetService.delete(passwordReset.getIdToken());
        usuarioService.save(user);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
