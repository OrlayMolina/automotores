package co.edu.uniquindio.automotores.infrastructure.controller;

import co.edu.uniquindio.automotores.application.dto.usuario.LoginDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.MensajeDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.TokenDTO;
import co.edu.uniquindio.automotores.application.exceptions.SesionNoIniciadaException;
import co.edu.uniquindio.automotores.application.usescases.cliente.ClienteServiceImpl;
import co.edu.uniquindio.automotores.application.usescases.usuario.UsuarioServiceImpl;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.automotores.infrastructure.adapters.config.JWTUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private JWTUtils jwtUtils;
    private final UsuarioServiceImpl usuarioService;

    public AuthenticationController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO){
        try {
            TokenDTO tokenDTO = usuarioService.iniciarSesion(loginDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false,  tokenDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, new TokenDTO(e.getMessage())));
        } catch (SesionNoIniciadaException e) {
            return ResponseEntity.status(UNAUTHORIZED).body(new MensajeDTO<>(true, new TokenDTO(e.getMessage())));
        }
    }
}
