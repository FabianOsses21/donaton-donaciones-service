package com.donaton.donaciones.dto.response;

import com.donaton.donaciones.enums.EstadoDonacion;
import com.donaton.donaciones.enums.TipoRecurso;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonacionResponseDTO {

    private Long id;
    private TipoRecurso tipoRecurso;
    private String detalleRecurso;
    private Integer cantidad;
    private String origen;
    private String nombreDonante;
    private String contactoDonante;
    private Long centroAcopioId;
    private Long necesidadId;
    private EstadoDonacion estado;
    private LocalDateTime fechaRegistro;
}
