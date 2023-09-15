import { URL_BASE, mostrarMensaje, enviarDatosGet, actualizardatos, eliminarRegistro} from "./utils/funciones.js";

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
                    <button type="button" class="btn btn-warning" id="editar${numeroItem}" data-index="${odontologo.id}" data-bs-toggle="modal" data-bs-target="#${"modalEdicionItem"+numeroItem}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                    <div class="modal fade" id="${"modalEdicionItem"+numeroItem}" tabindex="-1">
                        <div class="modal-dialog modal-lg">
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
                    <button type="button" class="btn btn-danger" id="eliminar${numeroItem}" data-index="${odontologo.id}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                </td>
            </tr>`; 
                numeroItem++;
            });

            if (listaOdontologos.length >0){
                tabla.innerHTML = contenidoTabla;

                listaOdontologos.forEach((odontologo, index) => {
                    const btnEditar = document.querySelector(`#editar${index + 1}`);
                    const btnEliminar = document.querySelector(`#eliminar${index + 1}`);
                    const btnGuardarCambios = document.querySelector(`#guardarCambios${index + 1}`);

                    const id = btnEditar.getAttribute('data-index');
                    
                    btnEditar.addEventListener("click", async () => {
                        // id = btnEditar.getAttribute('data-index');
                        console.log(id);
                        try{
                            const url = URL_BASE + '/odontologos/'+id;
                            const odontologoData = await enviarDatosGet(url);
                            const modal = document.getElementById(`modalEdicionItem${index+1}`);
                            modal.querySelector(`#matricula${index+1}`).value = odontologoData.matricula;
                            modal.querySelector(`#nombre${index+1}`).value = odontologoData.nombre;
                            modal.querySelector(`#apellido${index+1}`).value = odontologoData.apellido;
                        }catch(error){
                            console.log(error.message);
                        }
                    });

                    btnGuardarCambios.addEventListener('click', async () => {
                        const matricula = document.getElementById(`matricula${index+1}`).value;
                        const nombre = document.getElementById(`nombre${index+1}`).value;
                        const apellido = document.getElementById(`apellido${index+1}`).value;

                        // Crear un objeto con los datos a enviar
                        const odontologoModificado = {
                            id : parseInt(id),
                            matricula: matricula,
                            nombre : nombre,
                            apellido: apellido
                        };
                        console.log(id);
                        console.log(odontologoModificado);

                        try{
                            const urlArtualizar = URL_BASE+'/odontologos/actualizar';
                            const respuesta = await actualizardatos(urlArtualizar, odontologoModificado);
                            Swal.fire({
                                icon: 'success',
                                title: 'Cambios guardados con éxito',
                                text: 'Los cambios se han guardado correctamente.',
                            }).then(() => {
                                // Refrescar la página
                                location.reload();
                            });
                            
                        }catch(error){
                            Swal.fire({
                                icon: 'error',
                                title: 'Error al guardar los cambios',
                                text: 'Hubo un problema al intentar guardar los cambios.',
                            }).then(() => {
                                // Refrescar la página
                                location.reload();
                            });
                        }

                    });

                    btnEliminar.addEventListener("click", async () => {
                        console.log(id);
                        if (id !== null) {
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
                              }).then( async (result) => {
                                if (result.isConfirmed) {
                                    try{
                                        const urlDelete = URL_BASE+'/odontologos/eliminar/'+id;
                                        
                                        const estaEliminado = await eliminarRegistro(urlDelete);
                                        
                                        swalWithBootstrapButtons.fire(
                                            'Eliminado!',
                                            'El odontologo a sido eliminado.',
                                            'success'
                                        ).then(() => {
                                            // Refrescar la página
                                            location.reload();
                                        });

                                    }catch(error){
                                        swalWithBootstrapButtons.fire(
                                            'Cancelado',
                                            'error'
                                          ).then(() => {
                                            // Refrescar la página
                                            location.reload();
                                        });
                                    }
                                } else if (result.dismiss === Swal.DismissReason.cancel) {
                                  swalWithBootstrapButtons.fire(
                                    'Cancelado',
                                    'Se aborto la eliminación',
                                    'error'
                                  )
                                }
                              })
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