package co.edu.uniquindio.automotores.infrastructure.controller;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.MensajeDTO;
import co.edu.uniquindio.automotores.application.usescases.cliente.ClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final ClienteServiceImpl clienteService;

    @PostMapping ("/cliente/crear-cliente")
    public ResponseEntity<MensajeDTO<String>> crearCliente(@RequestBody ClienteDTO cliente) {
        clienteService.crearCliente(cliente);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta creada exitosamente"));
    }

}
