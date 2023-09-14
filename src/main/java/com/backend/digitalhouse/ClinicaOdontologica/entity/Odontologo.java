package com.backend.digitalhouse.ClinicaOdontologica.entity;

import javax.persistence.*;

@Entity
@Table(name = "ODONTOLOGOS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ODONTOLOGOS_MATRICULA"})
})
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ODONTOLOGOS_ID")
    private Long id;
    @Column(name = "ODONTOLOGOS_MATRICULA", nullable = false)
    private String matricula;
    @Column(name = "ODONTOLOGOS_NOMBRE", nullable = false, length = 25)
    private String nombre;
    @Column(name = "ODONTOLOGOS_APELLIDO", nullable = false, length = 50)
    private String apellido;

    public Odontologo(){}


    public Odontologo(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
