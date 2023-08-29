package com.backend.digitalhouse.ClinicaOdontologica.repository;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
