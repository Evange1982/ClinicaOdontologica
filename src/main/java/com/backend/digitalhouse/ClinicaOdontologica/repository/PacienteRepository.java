package com.backend.digitalhouse.ClinicaOdontologica.repository;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
