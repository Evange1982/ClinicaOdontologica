import { URL_BASE, mostrarMensaje, enviarDatosPost } from "./utils/funciones.js";

(function() {
    "use strict";
    document.addEventListener("DOMContentLoaded", function() {
        
        const formularioOdontologo = document.querySelector("#formularioPaciente");
    
        formularioOdontologo.addEventListener("submit", async function(event) {
            event.preventDefault();

            const data = {
                "nombre": document.querySelector("#nombre").value,
                "apellido": document.querySelector("#apellido").value,
                "dni" : parseInt(document.querySelector("#dni").value),
                "fechaIngreso" : document.querySelector("#fechaIngreso").value,
                "domicilio" : {
                    "calle" : document.querySelector("#calle").value,
                    "numero" : parseInt(document.querySelector("#altura").value),
                    "localidad" : document.querySelector("#localidad").value,
                    "provincia" : document.querySelector("#provincia").value
                } 
            }

            const url = URL_BASE + '/pacientes/registrar';
            console.log(data);
            try {
                const response = await enviarDatosPost(url, data);
                mostrarMensaje({icon: 'success',title: 'OK',text : 'alta de Paciente satisfactorio'});
            } catch (error) {
                const mensajeFinal = error.message;
                mostrarMensaje('error', mensajeFinal);
            } 
        });
    });
})();