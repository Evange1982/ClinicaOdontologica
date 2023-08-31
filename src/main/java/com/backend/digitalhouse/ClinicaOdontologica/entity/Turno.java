package com.backend.digitalhouse.ClinicaOdontologica.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TURNOS_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ODONTOLOGOS_ID")
    private Odontologo odontologo;
    @ManyToOne
    @JoinColumn(name = "PACIENTES_ID")
    private Paciente paciente;
    @Column(name = "TURNOS_FECHA", nullable = false)
    private LocalDateTime fechaYHora;

    public Turno(){}

    public Turno(Odontologo odontologo, Paciente paciente, LocalDateTime fechaYHora) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaYHora = fechaYHora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

}
