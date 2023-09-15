package com.backend.digitalhouse.ClinicaOdontologica.entity;

import javax.persistence.*;

@Entity
@Table(name = "DOMICILIOS")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOMICILIOS_ID")
    private Long id;

    @Column(name = "DOMICILIOS_CALLE", nullable = false, length = 500)
    private String calle;
    @Column(name = "DOMICILIOS_NUMERO", nullable = false)
    private int numero;
    @Column(name = "DOMICILIOS_LOCALIDAD", nullable = false, length = 50)
    private String localidad;
    @Column(name = "DOMICILIOS_PROVINCIA", nullable = false)
    private String provincia;

    public Domicilio(){}

    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Calle: " + calle + " - Numero: " + numero + " - Localidad: " + localidad + " - Provincia: " + provincia;
    }
}
