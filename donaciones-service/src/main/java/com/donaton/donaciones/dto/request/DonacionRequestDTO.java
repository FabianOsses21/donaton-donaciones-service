package com.donaton.donaciones.dto.request;

import com.donaton.donaciones.enums.TipoRecurso;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonacionRequestDTO {

    @NotNull(message = "El tipo de recurso es obligatorio")
    private TipoRecurso tipoRecurso;

    @NotBlank(message = "El detalle del recurso es obligatorio")
    private String detalleRecurso;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private Integer cantidad;

    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    private String nombreDonante;

    private String contactoDonante;

    private Long centroAcopioId;

    private Long necesidadId;
}
