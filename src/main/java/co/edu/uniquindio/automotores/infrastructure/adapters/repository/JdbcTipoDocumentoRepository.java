package co.edu.uniquindio.automotores.infrastructure.adapters.repository;

import co.edu.uniquindio.automotores.application.dto.tipodocumento.TipoDocumentoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.tipodocumento.ITipoDocumentoUsesCases;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTipoDocumentoRepository implements ITipoDocumentoUsesCases {
    @Override
    public List<TipoDocumentoDTO> tipoDocumentos() {
        return null;
    }
}
