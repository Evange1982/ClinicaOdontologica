import { URL_BASE, mostrarMensaje, enviarDatosGet, enviarDatosPost } from "./utils/funciones.js";

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
                <td>
                    <button type="button" class="btn btn-warning editar" data-index="${odontologo.id}" data-bs-toggle="modal" data-bs-target="#${"modalEdicionItem"+numeroItem}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                    <div class="modal fade" id="${"modalEdicionItem"+numeroItem}" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title"> Modificar Odontologo</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Item</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="item${numeroItem}" class="form-control" required placeholder="AA-1234567" value="${numeroItem}" disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Matricula</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="matricula${numeroItem}" class="form-control" required placeholder="AA-1234567" value="${odontologo.matricula}" disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Nombre</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="nombre${numeroItem}" class="form-control" value="${odontologo.nombre}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">apellido</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="apellido${numeroItem}" class="form-control" value="${odontologo.apellido}" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="buttom" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</buttom>
                                    <button type="buttom" class="btn btn-primary" id="guardarCambios${numeroItem}">Guardar cambios</buttom>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-danger eliminar" data-index="${odontologo.id}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                </td>
            </tr>`; 
                numeroItem++;
            });

            if (listaOdontologos.length >0){
                tabla.innerHTML = contenidoTabla;

                // Agregar eventos a los botones (solo si la tabla no está vacía)
                const editarBotones = document.querySelectorAll(".editar");
                const eliminarBotones = document.querySelectorAll(".eliminar");

                editarBotones.forEach((editarBtn , index)=> {
                    editarBtn.addEventListener("click", async () => {
                        const odontologoId = editarBtn.getAttribute('data-index');
                        try{
                            const url = URL_BASE + '/odontologos/'+odontologoId;
                            const odontologoData = await enviarDatosGet(url);
                            const modal = document.getElementById(`modalEdicionItem${index+1}`);
                            modal.querySelector(`#matricula${index+1}`).value = odontologoData.matricula;
                            modal.querySelector(`#nombre${index+1}`).value = odontologoData.nombre;
                            modal.querySelector(`#apellido${index+1}`).value = odontologoData.apellido;
                            // console.log(response);
                        }catch(error){
                            console.log(error.message);
                        }
                        if (index !== null) {
                            // Implementa la lógica para editar aquí usando listaOdontologos[index]
                            console.log(`Editar odontólogo número ${parseInt(index)}`);
                        }
                    });
                });
                
                // Agregar un evento de clic a los botones de "Guardar Cambios"
                const btnGuardarCambios = document.querySelectorAll('.guardarCambios*');
                console.log("boton guardar cambios "+btnGuardarCambios);
                btnGuardarCambios.forEach((guardarCambiosBtn, index) => {
                    guardarCambiosBtn.addEventListener('click', async () => {
                        // Obtener el índice del odontólogo desde el atributo data-index
                        const odontologoId = guardarCambiosBtn.getAttribute('data-index');

                        // Obtener los valores de los campos del modal
                        const matricula = document.getElementById(`matriculaEditar${index+1}`).value;
                        const nombre = document.getElementById(`nombreEditar${index+1}`).value;
                        const apellido = document.getElementById(`apellidoEditar${index+1}`).value;
                    
                        // Crear un objeto con los datos a enviar
                        const data = {
                            matricula,
                            nombre,
                            apellido
                        };

                        console.log(data);
                        // Realizar la solicitud POST
                        try {
                            url = URL_BASE+'/actualizar'
                            const respuesta = enviarDatosPost(url, data);
                            
                            console.log(respuesta);
                            // Cerrar el modal después de guardar los cambios
                            const modal = new bootstrap.Modal(document.getElementById(`modalEdicionItem${index}`));
                            modal.hide();

                            // Puedes realizar alguna acción adicional aquí después de guardar los cambios
                        } catch (error) {
                            console.error(error);
                        }
                    });
                });



                eliminarBotones.forEach(boton => {
                    boton.addEventListener("click", () => {
                        const index = boton.getAttribute("data-index");
                        if (index !== null) {
                            const swalWithBootstrapButtons = Swal.mixin({
                                customClass: {
                                  confirmButton: 'btn btn-success',
                                  cancelButton: 'btn btn-danger'
                                },
                                buttonsStyling: false
                              })
                              
                              swalWithBootstrapButtons.fire({
                                title: 'Estas seguro que desea eliminar este odontologo?',
                                text: "Esta accion no se puede revertir!",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonText: 'Si, Eliminar!',
                                cancelButtonText: 'No, Cancelar!',
                                reverseButtons: true
                              }).then((result) => {
                                if (result.isConfirmed) {
                                  swalWithBootstrapButtons.fire(
                                    'Eliminado!',
                                    'El odontologo a sido eliminado.',
                                    'success'
                                  )
                                } else if (result.dismiss === Swal.DismissReason.cancel) {
                                  swalWithBootstrapButtons.fire(
                                    'Cancelado',
                                    'Se aborto la eliminación',
                                    'error'
                                  )
                                }
                              })
                            console.log(`Eliminar odontólogo número ${parseInt(index)}`);
                        }
                    });
                });

            }else{
                tabla.innerHTML = '<tr><td colspan="5">No hay odontólogos para mostrar</td></tr>';
            }

        }catch (error){
            const mensaje = error.message;
            console.log(mensaje);
            mostrarMensaje({icon:'error',title: 'ERROR',text : mensaje});
        }finally{
            
        } 
        

    });


})();