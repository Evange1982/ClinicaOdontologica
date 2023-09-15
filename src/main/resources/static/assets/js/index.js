import { URL_BASE, mostrarMensaje, enviarDatosGet } from "./utils/funciones.js";

(function() {
    "use strict";
    
    document.addEventListener("DOMContentLoaded", async function() {
        
        const cantidadTurnosHtml = document.querySelector("#cantidadHtml");
        const url = URL_BASE + '/turnos/';
        
        try{
            const cantidadTurnos = await enviarDatosGet(url);
            
            cantidadTurnosHtml.innerHTML = (cantidadTurnos.length > 0) ? cantidadTurnos.length = cantidadTurnos.length : 0;
        }catch(error){
            const mensaje = error.message;
            console.log(mensaje);
            mostrarMensaje({icon:'error',title: 'ERROR',text : mensaje});
        }
    });
})();