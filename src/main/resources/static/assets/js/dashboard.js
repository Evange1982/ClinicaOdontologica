(function() {
    "use strict";
    const url_base = "http://localhost:8080";
    let logs = [
        {
            tipo: "A",
            activity: "paciente",
            date: "2023-08-31",
            message: "Se registro un nuevo paciente",
            name: "Juan Mercado"
        },
        {
            tipo: "A",
            activity: "paciente",
            date: "2023-08-31",
            message: "Se registro un nuevo paciente",
            name: "Evangelina Britos"
        },
        {
            tipo: "A",
            activity: "Turno",
            date: "2023-08-31",
            message: "Se cargo un nuevo Turno",
            name: ""
        },
        {
            tipo: "M",
            activity: "paciente",
            date: "2023-08-31",
            message: "Se modifico un nuevo paciente",
            name: "Evangelina Britos"
        },
        {
            tipo: "D",
            activity: "paciente",
            date: "2023-09-06",
            message: "Se Elimino un nuevo paciente",
            name: "Juan Mercado"
        },
    ]
    
    function obtenerEstadoFecha(fechaString) {
        const fechaIngresada = new Date(fechaString);
        const fechaActual = new Date();
    
        // Comparamos solo las fechas, sin tener en cuenta la hora
        fechaIngresada.setHours(0, 0, 0, 0);
        fechaActual.setHours(0, 0, 0, 0);
    
        const diferenciaDias = (fechaActual - fechaIngresada) / (1000 * 60 * 60 * 24) - 1;
    
        if (diferenciaDias === 0) {
            return "HOY";
        } else if (diferenciaDias === 1) {
            return "AYER";
        } else {
            return "+"+diferenciaDias;
        }
    }

    function construirHTMLActividades(logs) {
        
        const tipoRegistro = {
            A : "text-success",
            D : "text-danger",
            M : "text-warning"
        }

        const actividadesHTML = logs.map(log => {
            return `
                <div class="activity-item d-flex">
                    <div class="activite-label">${obtenerEstadoFecha(log.date)}</div>
                    <i class='bi bi-circle-fill activity-badge ${tipoRegistro[log.tipo]} align-self-start'></i>
                    <div class="activity-content">
                        ${log.message} 
                        ${log.name ? `<a href="#" class="fw-bold text-dark">${log.name}</a>` : ''}
                    </div>
                </div>
            `;
        });

        // Utiliza join para unir los strings en una sola cadena HTML
        return actividadesHTML.join('');
    }

    function construirHTMLCantidadTurno() {
        
    }

    function consultarBack(endpoint) {
        console.log("Consultando: " + url_base + endpoint);
        fetch(url_base + endpoint)
            .then((respuesta) => {
                console.log("Primer THEN");
                return respuesta.json();
            })
            .then((respuestaJS) => {
                console.log("Segundo THEN");
                console.log(respuestaJS);
                //renderizarElementos(respuestaJS);
            })
            .catch(err => {
                console.log("Error!!!");
                console.error(err)
            });
    }
    

    /*
        Programa Principal
    */
    
    document.addEventListener("DOMContentLoaded", function() {
    
        /*
            Se completa la card de actvivdades
        */
        const actividades = consultarBack("/odontologos/");
        const actividadesHTML = construirHTMLActividades(logs); 
        document.querySelector('#logs').innerHTML = actividadesHTML;

        /*
            Se completa la cantidad de turnos
        */
       const cantidadDeTurnos = consultarBack("");
    });

})();