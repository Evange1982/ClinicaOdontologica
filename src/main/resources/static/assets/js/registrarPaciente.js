(function() {
    "use strict";
    const url_base = "http://localhost:8080";
    
    async function enviarDatos(url, metodo, datos, callbackExito, callbackError) {
        const response = await fetch(url, {
            method: metodo,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });
            
        const data = await response.json();
        
        if (response.status != 201) {
            let mensaje = "";
            if (data !== undefined && data !== null) {
                if(response.status == 400){
                    mensaje = Object.values(data).join(',\n');
                }else{
                    mensaje = 'Error interno';
                }
            } else {
                mensaje = 'Error interno';
            }           
            throw new Error(mensaje);
        }

        return data;
    }

    function mostrarMensaje(icon, message) {
        console.log(message);
        Swal.fire({
            icon: icon,
            title: 'Oops...',
            text : message,
        });
    }

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