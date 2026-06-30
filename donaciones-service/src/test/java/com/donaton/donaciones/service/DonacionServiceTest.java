package com.donaton.donaciones.service;

import com.donaton.donaciones.dto.request.DonacionRequestDTO;
import com.donaton.donaciones.dto.response.DonacionResponseDTO;
import com.donaton.donaciones.enums.EstadoDonacion;
import com.donaton.donaciones.enums.TipoRecurso;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.repository.DonacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DonacionServiceTest {

    @Mock
    private DonacionRepository donacionRepository;

    @InjectMocks
    private DonacionService donacionService;

    @Test
    void deberiaGuardarDonacionCorrectamente() {
        DonacionRequestDTO request = crearRequest();
        Donacion donacionGuardada = crearDonacion();

        Mockito.when(donacionRepository.save(any(Donacion.class)))
                .thenReturn(donacionGuardada);

        DonacionResponseDTO resultado = donacionService.guardar(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(TipoRecurso.ALIMENTO, resultado.getTipoRecurso());
        assertEquals(EstadoDonacion.REGISTRADA, resultado.getEstado());
    }

    @Test
    void deberiaListarDonaciones() {
        Mockito.when(donacionRepository.findAll())
                .thenReturn(List.of(crearDonacion()));

        List<DonacionResponseDTO> resultado = donacionService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Arroz y fideos", resultado.get(0).getDetalleRecurso());
    }

    @Test
    void deberiaBuscarDonacionPorId() {
        Mockito.when(donacionRepository.findById(1L))
                .thenReturn(Optional.of(crearDonacion()));

        DonacionResponseDTO resultado = donacionService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan Pérez", resultado.getNombreDonante());
    }

    @Test
    void deberiaActualizarDonacion() {
        Donacion donacionExistente = crearDonacion();
        DonacionRequestDTO request = crearRequest();
        request.setTipoRecurso(TipoRecurso.ROPA);
        request.setDetalleRecurso("Chaquetas");
        request.setCantidad(5);
        request.setOrigen("Valparaíso");

        Mockito.when(donacionRepository.findById(1L))
                .thenReturn(Optional.of(donacionExistente));

        Mockito.when(donacionRepository.save(any(Donacion.class)))
                .thenReturn(donacionExistente);

        DonacionResponseDTO resultado = donacionService.actualizar(1L, request);

        assertNotNull(resultado);
        assertEquals(TipoRecurso.ROPA, resultado.getTipoRecurso());
        assertEquals("Chaquetas", resultado.getDetalleRecurso());
        assertEquals(5, resultado.getCantidad());
        assertEquals("Valparaíso", resultado.getOrigen());
    }

    @Test
    void deberiaCambiarEstadoDonacion() {
        Donacion donacion = crearDonacion();

        Mockito.when(donacionRepository.findById(1L))
                .thenReturn(Optional.of(donacion));

        Mockito.when(donacionRepository.save(any(Donacion.class)))
                .thenReturn(donacion);

        DonacionResponseDTO resultado = donacionService.cambiarEstado(1L, EstadoDonacion.ENTREGADA);

        assertNotNull(resultado);
        assertEquals(EstadoDonacion.ENTREGADA, resultado.getEstado());
    }

    @Test
    void deberiaEliminarDonacion() {
        Donacion donacion = crearDonacion();

        Mockito.when(donacionRepository.findById(1L))
                .thenReturn(Optional.of(donacion));

        donacionService.eliminar(1L);

        Mockito.verify(donacionRepository).delete(donacion);
    }

    @Test
    void deberiaBuscarPorEstado() {
        Mockito.when(donacionRepository.findByEstado(EstadoDonacion.REGISTRADA))
                .thenReturn(List.of(crearDonacion()));

        List<DonacionResponseDTO> resultado =
                donacionService.buscarPorEstado(EstadoDonacion.REGISTRADA);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(EstadoDonacion.REGISTRADA, resultado.get(0).getEstado());
    }

    @Test
    void deberiaBuscarPorTipoRecurso() {
        Mockito.when(donacionRepository.findByTipoRecurso(TipoRecurso.ALIMENTO))
                .thenReturn(List.of(crearDonacion()));

        List<DonacionResponseDTO> resultado =
                donacionService.buscarPorTipoRecurso(TipoRecurso.ALIMENTO);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(TipoRecurso.ALIMENTO, resultado.get(0).getTipoRecurso());
    }

    @Test
    void deberiaBuscarPorNecesidad() {
        Mockito.when(donacionRepository.findByNecesidadId(10L))
                .thenReturn(List.of(crearDonacion()));

        List<DonacionResponseDTO> resultado =
                donacionService.buscarPorNecesidad(10L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(10L, resultado.get(0).getNecesidadId());
    }

    @Test
    void deberiaBuscarPorCentroAcopio() {
        Mockito.when(donacionRepository.findByCentroAcopioId(20L))
                .thenReturn(List.of(crearDonacion()));

        List<DonacionResponseDTO> resultado =
                donacionService.buscarPorCentroAcopio(20L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(20L, resultado.get(0).getCentroAcopioId());
    }

    @Test
    void deberiaLanzarErrorCuandoNoExisteDonacion() {
        Mockito.when(donacionRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> donacionService.buscarPorId(99L)
        );

        assertEquals("Donacion no encontrada", exception.getMessage());
    }

    private DonacionRequestDTO crearRequest() {
        DonacionRequestDTO request = new DonacionRequestDTO();
        request.setTipoRecurso(TipoRecurso.ALIMENTO);
        request.setDetalleRecurso("Arroz y fideos");
        request.setCantidad(10);
        request.setOrigen("Santiago");
        request.setNombreDonante("Juan Pérez");
        request.setContactoDonante("juan@test.com");
        request.setCentroAcopioId(20L);
        request.setNecesidadId(10L);
        return request;
    }

    private Donacion crearDonacion() {
        Donacion donacion = new Donacion();
        donacion.setId(1L);
        donacion.setTipoRecurso(TipoRecurso.ALIMENTO);
        donacion.setDetalleRecurso("Arroz y fideos");
        donacion.setCantidad(10);
        donacion.setOrigen("Santiago");
        donacion.setNombreDonante("Juan Pérez");
        donacion.setContactoDonante("juan@test.com");
        donacion.setCentroAcopioId(20L);
        donacion.setNecesidadId(10L);
        donacion.setEstado(EstadoDonacion.REGISTRADA);
        donacion.setFechaRegistro(LocalDateTime.now());
        return donacion;
    }
}