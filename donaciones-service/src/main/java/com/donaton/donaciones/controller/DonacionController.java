package com.donaton.donaciones.controller;

import com.donaton.donaciones.dto.request.DonacionRequestDTO;
import com.donaton.donaciones.dto.response.DonacionResponseDTO;
import com.donaton.donaciones.enums.EstadoDonacion;
import com.donaton.donaciones.enums.TipoRecurso;
import com.donaton.donaciones.service.DonacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DonacionController {

    private final DonacionService donacionService;

    @GetMapping
    public ResponseEntity<List<DonacionResponseDTO>> listar() {
        return ResponseEntity.ok(donacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonacionResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(donacionService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DonacionResponseDTO> guardar(@Valid @RequestBody DonacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donacionService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonacionResponseDTO> actualizar(@PathVariable("id") Long id, @Valid @RequestBody DonacionRequestDTO dto) {
        return ResponseEntity.ok(donacionService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        donacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

  @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<DonacionResponseDTO> cambiarEstado(
        @PathVariable("id") Long id,
        @PathVariable("estado") EstadoDonacion estado) {
    return ResponseEntity.ok(donacionService.cambiarEstado(id, estado));
}

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DonacionResponseDTO>> buscarPorEstado(@PathVariable("estado") EstadoDonacion estado) {
        return ResponseEntity.ok(donacionService.buscarPorEstado(estado));
    }

    @GetMapping("/tipo/{tipoRecurso}")
    public ResponseEntity<List<DonacionResponseDTO>> buscarPorTipoRecurso(@PathVariable("tipoRecurso") TipoRecurso tipoRecurso) {
        return ResponseEntity.ok(donacionService.buscarPorTipoRecurso(tipoRecurso));
    }

    @GetMapping("/necesidad/{necesidadId}")
    public ResponseEntity<List<DonacionResponseDTO>> buscarPorNecesidad(@PathVariable("necesidadId") Long necesidadId) {
        return ResponseEntity.ok(donacionService.buscarPorNecesidad(necesidadId));
    }

    @GetMapping("/centro-acopio/{centroAcopioId}")
    public ResponseEntity<List<DonacionResponseDTO>> buscarPorCentroAcopio(@PathVariable("centroAcopioId") Long centroAcopioId) {
        return ResponseEntity.ok(donacionService.buscarPorCentroAcopio(centroAcopioId));
    }
}
