import { URL_BASE, mostrarMensaje, enviarDatos } from "./utils/funciones.js";

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

            const url = url_base + '/pacientes/registrar';
            console.log(data);
            try {
                const response = await enviarDatos(url, 'POST', data);
                //console.log(response);
                let mensaje = ' Se agrego el Odontologo \n'+data.nombre+' '+data.apellido;
                mostrarMensaje('success', mensaje);
            } catch (error) {
                const mensajeFinal = error.message;
                mostrarMensaje('error', mensajeFinal);
            }

            console.log(data);  
        });
    });
})();