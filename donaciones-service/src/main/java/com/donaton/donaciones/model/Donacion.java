package com.donaton.donaciones.model;

import com.donaton.donaciones.enums.EstadoDonacion;
import com.donaton.donaciones.enums.TipoRecurso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "donaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoRecurso tipoRecurso;

    private String detalleRecurso;

    private Integer cantidad;

    private String origen;

    private String nombreDonante;

    private String contactoDonante;

    private Long centroAcopioId;

    private Long necesidadId;

    @Enumerated(EnumType.STRING)
    private EstadoDonacion estado;

    private LocalDateTime fechaRegistro;
}
