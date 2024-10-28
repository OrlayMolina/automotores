package co.edu.uniquindio.automotores.infrastructure.controller;

import co.edu.uniquindio.automotores.application.usescases.cliente.ClienteServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final ClienteServiceImpl clienteService;

    public AuthenticationController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }
}
