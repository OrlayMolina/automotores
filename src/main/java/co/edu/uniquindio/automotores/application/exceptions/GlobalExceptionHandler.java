package co.edu.uniquindio.automotores.application.exceptions;

import co.edu.uniquindio.automotores.application.dto.usuario.MensajeDTO;
import co.edu.uniquindio.automotores.application.dto.usuario.ValidacionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> generalException(Exception e){
        return ResponseEntity.internalServerError().body( new MensajeDTO<>(true, e.getMessage())
        );
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO<List<ValidacionDTO>>> validationException(
            MethodArgumentNotValidException ex ) {
        List<ValidacionDTO> errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();
        for (FieldError e: results.getFieldErrors()) {
            errores.add( new ValidacionDTO(e.getField(), e.getDefaultMessage()) );
        }
        return ResponseEntity.badRequest().body( new MensajeDTO<>(true, errores) );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MensajeDTO<String>> handleAccessDeniedException(AccessDeniedException ex){
        String message = "Tu cargo no tiene permisos para esta acción!";
        MensajeDTO<String> mensajeDTO = new MensajeDTO<>(true, message);
        return new ResponseEntity<>(mensajeDTO, HttpStatus.FORBIDDEN);
    }
}
