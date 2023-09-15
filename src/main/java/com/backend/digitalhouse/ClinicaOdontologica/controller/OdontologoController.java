package com.backend.digitalhouse.ClinicaOdontologica.controller;

import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.modificado.OdontologoModificacionEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.digitalhouse.ClinicaOdontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.BadRequestException;
import com.backend.digitalhouse.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.digitalhouse.ClinicaOdontologica.service.IOdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

     private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //POST
    @Operation(summary = "Registro de un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@Valid @RequestBody OdontologoEntradaDto odontologoEntrada) throws BadRequestException {
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologoEntrada), HttpStatus.CREATED);
    }

    //PUT
    @Operation(summary = "Actualización de un odontologo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "UServer error",
                    content = @Content)
    })
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@Valid @RequestBody OdontologoModificacionEntradaDto odontologo) throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologo), HttpStatus.OK);
    }

    @Operation(summary = "Búsqueda de un odontólogo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public OdontologoSalidaDto buscarOdontologoPorId(@PathVariable Long id){
        return odontologoService.buscarOdontologoPorId(id);
    }

    @Operation(summary = "Eliminación de un odontologo por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Odontólogo eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {odontologoService.eliminarOdontologo(id);
    }
    @GetMapping("/")
    public List<OdontologoSalidaDto> listarOdontologo(){
        return odontologoService.listarOdontologos();
    }
}


