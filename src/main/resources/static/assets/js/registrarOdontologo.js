import { URL_BASE, mostrarMensaje, enviarDatosPost } from "./utils/funciones.js";

(function() {
    "use strict";
    
    document.addEventListener("DOMContentLoaded", function() {
        
        const formularioOdontologo = document.querySelector("#formularioOdontologo");
    
        formularioOdontologo.addEventListener("submit", async function(event) {
            event.preventDefault();
            
            const url = URL_BASE + '/odontologos/registrar';
            
            const data = {
                matricula: document.querySelector("#matricula").value,
                nombre: document.querySelector("#nombre").value,
                apellido: document.querySelector("#apellido").value
            };
    
            try {
                const response = await enviarDatosPost(url, data);
                let mensaje = ' Se agrego el Odontologo \n'+data.nombre+' '+data.apellido;
                mostrarMensaje({icon: 'success',title: 'OK',text : mensaje});
            } catch (error) {
                const mensaje = error.message;
                console.log(mensaje);
                mostrarMensaje({icon:'error',title: 'ERROR',text : mensaje});
            }
        });
    });
})();