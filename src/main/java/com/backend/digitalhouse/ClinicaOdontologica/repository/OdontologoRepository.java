package com.backend.digitalhouse.ClinicaOdontologica.repository;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query("SELECT d FROM Odontologo d WHERE d.matricula  = ?1")
    List<Odontologo> buscarOdontologoPorMatricula(String matricula);
}
