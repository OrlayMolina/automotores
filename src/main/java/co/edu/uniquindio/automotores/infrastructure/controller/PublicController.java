package co.edu.uniquindio.automotores.infrastructure.controller;

import co.edu.uniquindio.automotores.application.dto.empleado.TipoEmpleadoDTO;
import co.edu.uniquindio.automotores.application.dto.tipodocumento.TipoDocumentoDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.MensajeDTO;
import co.edu.uniquindio.automotores.application.usescases.empleado.TipoEmpleadoServiceImpl;
import co.edu.uniquindio.automotores.application.usescases.tipodocumento.TipoDocumentoServiceImpl;
import co.edu.uniquindio.automotores.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final TipoDocumentoServiceImpl tipoDocumentoService;
    private final TipoEmpleadoServiceImpl tipoEmpleadoService;

    @GetMapping("/tipos-documento")
    public ResponseEntity<MensajeDTO<List<TipoDocumentoDTO>>> tipoDocumentos(){
        try {
            List<TipoDocumentoDTO> tipos = tipoDocumentoService.tipoDocumentos();
            return ResponseEntity.ok(new MensajeDTO<>(false, tipos));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, List.of(new TipoDocumentoDTO(1L, "", "Tipo de Documentos no encontrados!"))));
        }
    }

    @GetMapping("/empleados/cargos")
    public ResponseEntity<MensajeDTO<List<TipoEmpleadoDTO>>> cargos(){
        try {
            List<TipoEmpleadoDTO> cargos = tipoEmpleadoService.cargos();
            return ResponseEntity.ok(new MensajeDTO<>(false, cargos));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new MensajeDTO<>(true, List.of(new TipoEmpleadoDTO(1L, "Cargos de empleados no encontrados!"))));
        }
    }
}
