package com.backend.digitalhouse.ClinicaOdontologica.repository;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

}
