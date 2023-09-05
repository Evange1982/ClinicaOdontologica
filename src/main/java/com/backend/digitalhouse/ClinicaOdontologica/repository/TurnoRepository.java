package com.backend.digitalhouse.ClinicaOdontologica.repository;

import com.backend.digitalhouse.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository <Turno, Long >{

    List<Turno> findByPacienteApellido(String apellido);
    //findBy es una parte clave de la convención de nomenclatura de Spring Data JPA que te permite
    // declarar consultas de búsqueda personalizadas en función de los campos de tus entidades sin necesidad de
    // escribir la consulta SQL completa manualmente. Spring Data JPA se encarga de generar automáticamente la
    // consulta SQL correspondiente basada en el nombre del método.
    @Query(value = "SELECT t.* FROM Turno t JOIN Odontologo o ON t.odontologo_id = o.id WHERE o.apellido = :apellido", nativeQuery = true)
    List<Turno> findByOdontologoApellido(@Param("apellido") String apellido);

    Turno registrar(Turno map);
}

>>>>>>> 6b4e9dae659d77f4def0d943085bb9bc0f918626
