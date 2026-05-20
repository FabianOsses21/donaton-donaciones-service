package com.donaton.donaciones.service;

import com.donaton.donaciones.dto.request.DonacionRequestDTO;
import com.donaton.donaciones.dto.response.DonacionResponseDTO;
import com.donaton.donaciones.enums.EstadoDonacion;
import com.donaton.donaciones.enums.TipoRecurso;
import com.donaton.donaciones.mapper.DonacionMapper;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.repository.DonacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonacionService {

    private final DonacionRepository donacionRepository;

    public List<DonacionResponseDTO> listar() {
        return donacionRepository.findAll()
                .stream()
                .map(DonacionMapper::toResponseDTO)
                .toList();
    }

    public DonacionResponseDTO buscarPorId(Long id) {
        return DonacionMapper.toResponseDTO(obtenerEntidadPorId(id));
    }

    public DonacionResponseDTO guardar(DonacionRequestDTO dto) {
        Donacion donacion = DonacionMapper.toEntity(dto);
        donacion.setFechaRegistro(LocalDateTime.now());
        donacion.setEstado(EstadoDonacion.REGISTRADA);

        return DonacionMapper.toResponseDTO(donacionRepository.save(donacion));
    }

    public DonacionResponseDTO actualizar(Long id, DonacionRequestDTO dto) {
        Donacion donacion = obtenerEntidadPorId(id);

        donacion.setTipoRecurso(dto.getTipoRecurso());
        donacion.setDetalleRecurso(dto.getDetalleRecurso());
        donacion.setCantidad(dto.getCantidad());
        donacion.setOrigen(dto.getOrigen());
        donacion.setNombreDonante(dto.getNombreDonante());
        donacion.setContactoDonante(dto.getContactoDonante());
        donacion.setCentroAcopioId(dto.getCentroAcopioId());
        donacion.setNecesidadId(dto.getNecesidadId());

        return DonacionMapper.toResponseDTO(donacionRepository.save(donacion));
    }

    public void eliminar(Long id) {
        Donacion donacion = obtenerEntidadPorId(id);
        donacionRepository.delete(donacion);
    }

    public DonacionResponseDTO cambiarEstado(Long id, EstadoDonacion nuevoEstado) {
        Donacion donacion = obtenerEntidadPorId(id);
        donacion.setEstado(nuevoEstado);

        return DonacionMapper.toResponseDTO(donacionRepository.save(donacion));
    }

    public List<DonacionResponseDTO> buscarPorEstado(EstadoDonacion estado) {
        return donacionRepository.findByEstado(estado)
                .stream()
                .map(DonacionMapper::toResponseDTO)
                .toList();
    }

    public List<DonacionResponseDTO> buscarPorTipoRecurso(TipoRecurso tipoRecurso) {
        return donacionRepository.findByTipoRecurso(tipoRecurso)
                .stream()
                .map(DonacionMapper::toResponseDTO)
                .toList();
    }

    public List<DonacionResponseDTO> buscarPorNecesidad(Long necesidadId) {
        return donacionRepository.findByNecesidadId(necesidadId)
                .stream()
                .map(DonacionMapper::toResponseDTO)
                .toList();
    }

    public List<DonacionResponseDTO> buscarPorCentroAcopio(Long centroAcopioId) {
        return donacionRepository.findByCentroAcopioId(centroAcopioId)
                .stream()
                .map(DonacionMapper::toResponseDTO)
                .toList();
    }

    private Donacion obtenerEntidadPorId(Long id) {
        return donacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donacion no encontrada"));
    }
}
