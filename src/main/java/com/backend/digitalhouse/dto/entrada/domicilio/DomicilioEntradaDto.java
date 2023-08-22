package com.backend.digitalhouse.dto.entrada.domicilio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioEntradaDto {

    //@NotNull(message = "La calle no puede ser nula")
    //@NotBlanck(message = "Debe especificarse el nombre de la calle")
    private String calle;

    //@NotNull(message = "El numero no se puede ser nulo")
    //@NotBlanck(message = "Debe especificarse el numero")
    //@Digits(integer = 8, fraction = 0, message = "El numero debe tener como maximo 8 digitos")
    //@Pattern(regexp = "\\d{1-8}", message = "El numero debe tener como maximo 8 digitos")
    private int numero;

    //@NotNull(message = "La localida no puede ser nula")
    //@NotBlanck(message = "Debe especificarse el nombre de la localidad")
    private String localidad;

    //@NotNull(message = "La provincia no puede ser nula")
    //@NotBlanck(message = "Debe especificarse el nombre de la provincia")
    private String provincia;

    public DomicilioEntradaDto(){

    }

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
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
}
