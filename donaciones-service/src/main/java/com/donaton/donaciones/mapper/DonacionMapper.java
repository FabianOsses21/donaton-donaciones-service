package com.donaton.donaciones.mapper;

import com.donaton.donaciones.dto.request.DonacionRequestDTO;
import com.donaton.donaciones.dto.response.DonacionResponseDTO;
import com.donaton.donaciones.model.Donacion;

public class DonacionMapper {

    private DonacionMapper() {
    }

    public static Donacion toEntity(DonacionRequestDTO dto) {
        return Donacion.builder()
                .tipoRecurso(dto.getTipoRecurso())
                .detalleRecurso(dto.getDetalleRecurso())
                .cantidad(dto.getCantidad())
                .origen(dto.getOrigen())
                .nombreDonante(dto.getNombreDonante())
                .contactoDonante(dto.getContactoDonante())
                .centroAcopioId(dto.getCentroAcopioId())
                .necesidadId(dto.getNecesidadId())
                .build();
    }

    public static DonacionResponseDTO toResponseDTO(Donacion donacion) {
        return DonacionResponseDTO.builder()
                .id(donacion.getId())
                .tipoRecurso(donacion.getTipoRecurso())
                .detalleRecurso(donacion.getDetalleRecurso())
                .cantidad(donacion.getCantidad())
                .origen(donacion.getOrigen())
                .nombreDonante(donacion.getNombreDonante())
                .contactoDonante(donacion.getContactoDonante())
                .centroAcopioId(donacion.getCentroAcopioId())
                .necesidadId(donacion.getNecesidadId())
                .estado(donacion.getEstado())
                .fechaRegistro(donacion.getFechaRegistro())
                .build();
    }
}
