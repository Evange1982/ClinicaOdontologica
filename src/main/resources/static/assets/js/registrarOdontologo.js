(function() {
    "use strict";
    const url_base = "http://localhost:8080";
    
    async function enviarDatos(url, metodo, datos, callbackExito, callbackError) {
        try {
            const response = await fetch(url, {
                method: metodo,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datos)
            });
    
            if (!response.ok) {
                throw new Error('Error en la solicitud.');
            }
    
            const data = await response.json();
    
            if (typeof callbackExito === 'function') {
                callbackExito(data);
            }
        } catch (error) {
            if (typeof callbackError === 'function') {
                callbackError(error);
            }
        }
    }

    function mostrarMensaje(icon, message) {
        Swal.fire({
            position: 'top-end',
            icon: icon,
            title: message,
            showConfirmButton: false,
            timer: 1500
        });
    }
    
    document.addEventListener("DOMContentLoaded", function() {
        
        const formularioOdontologo = document.querySelector("#formularioOdontologo");
    
        formularioOdontologo.addEventListener("submit", async function(event) {
            console.log("Se envia los datos del formulario");
            event.preventDefault();
            const matricula = document.querySelector("#matricula").value;
            const nombre = document.querySelector("#nombre").value;
            const apellido = document.querySelector("#apellido").value;
    
            const data = {
                matricula: matricula,
                nombre: nombre,
                apellido: apellido
            };
    
            const url = url_base + '/odontologos/registrar';
    
            try {
                const response = await enviarDatos(url, 'POST', data);
                console.log(data);
                let mensaje = ' Se agrego el Odontologo \n'+data.nombre+' '+data.apellido;
                mostrarMensaje('success', mensaje);
            } catch (error) {
                mostrarMensaje('error', error);
            }
        });
    });
})();