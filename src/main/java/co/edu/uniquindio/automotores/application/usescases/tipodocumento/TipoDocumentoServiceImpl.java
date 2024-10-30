package co.edu.uniquindio.automotores.application.usescases.tipodocumento;

import co.edu.uniquindio.automotores.application.dto.tipodocumento.TipoDocumentoDTO;
import co.edu.uniquindio.automotores.domain.ports.in.tipodocumento.ITipoDocumentoUsesCases;
import co.edu.uniquindio.automotores.infrastructure.adapters.repository.JdbcTipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDocumentoServiceImpl implements ITipoDocumentoUsesCases {

    private final JdbcTipoDocumentoRepository tipoDocumentoRepository;
    @Override
    public List<TipoDocumentoDTO> tipoDocumentos() {
        return null;
    }
}
