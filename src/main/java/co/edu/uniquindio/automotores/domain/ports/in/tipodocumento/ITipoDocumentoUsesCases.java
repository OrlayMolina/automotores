package co.edu.uniquindio.automotores.domain.ports.in.tipodocumento;

import co.edu.uniquindio.automotores.application.dto.cliente.ClienteDTO;
import co.edu.uniquindio.automotores.application.dto.tipodocumento.TipoDocumentoDTO;

import java.util.List;

public interface ITipoDocumentoUsesCases {
    List<TipoDocumentoDTO> tipoDocumentos();
}
