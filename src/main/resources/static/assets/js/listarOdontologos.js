import { URL_BASE, mostrarMensaje, enviarDatosGet } from "./utils/funciones.js";

(function() {
    "use strict";
    const url_base = "http://localhost:8080";
    
    document.addEventListener("DOMContentLoaded", async function() {
        
        const url = URL_BASE + '/odontologos/';
        const tabla = document.querySelector("#tablaOdontologos");
        let contenidoTabla = '';

        try{
            const listaOdontologos = await enviarDatosGet(url);
            let numeroItem = 1;
            listaOdontologos.forEach(odontologo => {
                contenidoTabla += `<tr>
                <th scope="row">${numeroItem}</th>
                <td>${odontologo.matricula}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
            </tr>`; 
                numeroItem++;
            });
            
        }catch (error){
            const mensaje = error.message;
            console.log(mensaje);
            mostrarMensaje({icon:'error',title: 'ERROR',text : mensaje});
        }finally{
            tabla.innerHTML = contenidoTabla;
        }       
    });
})();