import { URL_BASE, mostrarMensaje, enviarDatosGet, enviarDatosPost} from "./utils/funciones.js";
(function() {
    "use strict";

    document.addEventListener("DOMContentLoaded", async function() {
        const urlOdontologos= URL_BASE + '/odontologos/';
        const urlPacientes = URL_BASE + '/pacientes/';
        const urlTurno = URL_BASE + '/turnos/registrar';

        let odontologoHtml = '';
        let pacienteHtml = '';
        const selecOdontologo = document.querySelector("#selectOdontologo");
        const selecPaciente = document.querySelector("#selectPaciente");
       
        selecOdontologo.innerHTML = ''; // Limpia el contenido previo
        selecPaciente.innerHTML = '';
       
        try{
            const listaPacientes = await enviarDatosGet(urlPacientes);
            const listaOdontologos = await enviarDatosGet(urlOdontologos);
            
            for (let i = 0; i < listaOdontologos.length; i++) {
                const odontologo = listaOdontologos[i];
                const option = document.createElement('option');
                option.value = odontologo.id;
                option.textContent = odontologo.nombre;
                selecOdontologo.appendChild(option);
            }
            
            for (let i = 0; i < listaPacientes.length; i++) {
                const paciente = listaPacientes[i];
                const option = document.createElement('option');
                option.value = paciente.id;
                option.textContent = paciente.nombre;
                selecPaciente.appendChild(option);
            }

            const data = {
                "pacienteId": 1,
                "odontologoId": 1,
                "fechaYHora": "2023-09-19 15:30"
            }

            const formularioTurno = document.querySelector("#formularioturno");
            
            formularioTurno.addEventListener("submit", async function(event) {
                event.preventDefault();
                
                const selectOdontologo = document.querySelector("#selectOdontologo").value;
                const selectPaciente = document.querySelector("#selectPaciente").value;
                const inputDate = document.querySelector("#inputDate").value;
                const inputTime = document.querySelector("#inputTime").value;

                // Realizar la conversión de formato de fecha y hora
                const fechaYHora = `${inputDate} ${inputTime}`;

                // Crear el objeto JSON con los datos
                const datos = {
                    pacienteId: selectPaciente,
                    odontologoId: selectOdontologo,
                    fechaYHora: fechaYHora,
                };
                try{
                    const response = await enviarDatosPost(urlTurno, datos);
                    mostrarMensaje({icon: 'success', title: 'OK', text : 'Turno registrado correctamente'});
                    console.log(datos);
                }catch(error){
                    const mensaje = error.message;
                    console.log(mensaje);
                    mostrarMensaje({icon:'error',title: 'ERROR',text : mensaje});
                }
                
            });
            console.log("A esta parte si llegamos");
        }catch(error){
            const mensaje = error.message;
            console.log(mensaje);
            mostrarMensaje({icon:'error',title: 'ERROR',text : mensaje});
        }

    });
})();