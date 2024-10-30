package co.edu.uniquindio.automotores.application.usescases.usuario;

import co.edu.uniquindio.automotores.application.dto.usuario.LoginDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.TokenDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.automotores.application.exceptions.SesionNoIniciadaException;
import co.edu.uniquindio.automotores.domain.ports.in.usuario.IUsuarioUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.config.JWTUtils;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioUsesCases {

    private final JdbcUsuarioRepository usuarioRepository;
    private final JWTUtils jwtUtils;

    public TokenDTO iniciarSesion(LoginDTO loginDTO){

        UsuarioDTO usuarioDTO = usuarioRepository.obtenerUsuarioPorCorreo(loginDTO.correo());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if( !passwordEncoder.matches(loginDTO.password(), usuarioDTO.password()) ) {
            throw new SesionNoIniciadaException("La contraseña es incorrecta");
        }
        Map<String, Object> map = construirClaims(usuarioDTO);
        return new TokenDTO( jwtUtils.generarToken(usuarioDTO.correo(), map) );
    }
    @Override
    public boolean verificarPermisos() {
        return false;
    }

    private Map<String, Object> construirClaims(UsuarioDTO usuario) {
        return Map.of(
                "id_empleado", usuario.id_empleado(),
                "correo", usuario.correo()
        );
    }
}
