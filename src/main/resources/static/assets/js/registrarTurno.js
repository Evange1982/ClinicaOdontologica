import { URL_BASE, mostrarMensaje, enviarDatosGet, actualizardatos, eliminarRegistro} from "./utils/funciones.js";
(function() {
    "use strict";

    document.addEventListener("DOMContentLoaded", async function() {
        const urlOdontologos= URL_BASE + '/odontologos/';
        const urlPacientes = URL_BASE + '/pacientes/';
        let odontologoHtml = '';
        let pacienteHtml = '';
        try{
            const listaPacientes = await enviarDatosGet(urlPacientes);
            const listaOdontologos = await enviarDatosGet(urlOdontologos);

            selecOdontologo = document.querySelector("#selectOdontologo");
            selecPaciente = document.querySelector("#selectselectPaciente");

            listaOdontologos.forEach(odontologo => {
                odontologoHtml += `<option value="${odontologo.id}">${odontologo.nombre}</option>
                `
            });
            if(listaOdontologos.size > 0){
                selecOdontologo.innerHTML = odontologoHtml;
            }
            listaPacientes.forEach((paciente) =>{
                pacienteHtml += `<option value="${paciente.id}">${paciente.nombre}</option>
                `
            })
            if(listaPacientes.size > 0){
                selecPaciente.innerHTML = pacienteHtml;
            }
            console.log("Hola mundo");
        }catch(error){

        }

    });
})();