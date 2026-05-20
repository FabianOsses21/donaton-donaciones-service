package com.donaton.donaciones.repository;

import com.donaton.donaciones.enums.EstadoDonacion;
import com.donaton.donaciones.enums.TipoRecurso;
import com.donaton.donaciones.model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {

    List<Donacion> findByEstado(EstadoDonacion estado);

    List<Donacion> findByTipoRecurso(TipoRecurso tipoRecurso);

    List<Donacion> findByNecesidadId(Long necesidadId);

    List<Donacion> findByCentroAcopioId(Long centroAcopioId);
}
