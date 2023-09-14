import { URL_BASE, mostrarMensaje, enviarDatosGet, actualizardatos, eliminarRegistro} from "./utils/funciones.js";
(function() {
    "use strict";

    document.addEventListener("DOMContentLoaded", async function() {
        const tabla = document.querySelector("#tablaPacientes");
        const url = URL_BASE + '/pacientes/';
        let contenidoTabla = '';
        try{
            const listaPacientes = await enviarDatosGet(url);
            console.log(listaPacientes);
            let numeroItem = 1;
            listaPacientes.forEach(paciente => {
                console.log(numeroItem);
                contenidoTabla += `<tr>
                <th scope="row">${numeroItem}</th>
                <td>${paciente.nombre}</td>
                <td>${paciente.apellido}</td>
                <td>${paciente.dni}</td>
                <td>${paciente.fechaIngreso}</td>
                <td>${paciente.domicilio.calle}</td>
                <td>${paciente.domicilio.numero}</td>
                <td>${paciente.domicilio.localidad}</td>
                <td>${paciente.domicilio.provincia}</td>
                <td>
                    <button type="button" class="btn btn-warning" id="editar${numeroItem}" data-index="${paciente.id}" data-bs-toggle="modal" data-bs-target="#${"modalEdicionItem"+numeroItem}">
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
                                            <input type="text" id="item${numeroItem}" class="form-control" value="${numeroItem}" disabled>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Nombre</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="nombre${numeroItem}" class="form-control" required placeholder="AA-1234567" value="${paciente.nombre}">
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Apellido</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="apellido${numeroItem}" class="form-control" value="${paciente.apellido}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">DNI</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="dni${numeroItem}" class="form-control" value="${paciente.dni}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">fechaIngreso</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="fechaIngreso${numeroItem}" class="form-control" value="${paciente.dni}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Calle</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="calle${numeroItem}" class="form-control" value="${paciente.domicilio.calle}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Numero</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="numero${numeroItem}" class="form-control" value="${paciente.domicilio.numero}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Localidad</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="localidad${numeroItem}" class="form-control" value="${paciente.domicilio.localidad}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label for="inputText" class="col-sm-2 col-form-label">Provincia</label>
                                        <div class="col-sm-10">
                                            <input type="text" id="provincia${numeroItem}" class="form-control" value="${paciente.domicilio.provincia}" required>
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
                    <button type="button" class="btn btn-danger" id="eliminar${numeroItem}" data-index="${paciente.id}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                </td>
            </tr>`; 
            numeroItem++;
            });

            if (listaPacientes.length >0){
                console.log("Hay pacientes");
                tabla.innerHTML = contenidoTabla;
                listaPacientes.forEach((odontologo, index) => {
                    const btnEditar = document.querySelector(`#editar${index + 1}`);
                    const btnEliminar = document.querySelector(`#eliminar${index + 1}`);
                    const btnGuardarCambios = document.querySelector(`#guardarCambios${index + 1}`);

                    const id = btnEditar.getAttribute('data-index');

                    btnEditar.addEventListener("click", async () => {
                        // id = btnEditar.getAttribute('data-index');
                        console.log(id);
                        try{
                            const url = URL_BASE + '/pacientes/'+id;
                            const pacienteData = await enviarDatosGet(url);
                            const modal = document.getElementById(`modalEdicionItem${index+1}`);
                            modal.querySelector(`#nombre${index+1}`).value = pacienteData.nombre;
                            modal.querySelector(`#apellido${index+1}`).value = pacienteData.apellido;
                            modal.querySelector(`#dni${index+1}`).value = pacienteData.dni;
                            modal.querySelector(`#fechaIngreso${index+1}`).value = pacienteData.fechaIngreso;
                            modal.querySelector(`#calle${index+1}`).value = pacienteData.domicilio.calle;
                            modal.querySelector(`#numero${index+1}`).value = pacienteData.domicilio.numero;
                            modal.querySelector(`#localidad${index+1}`).value = pacienteData.domicilio.localidad;
                            modal.querySelector(`#provincia${index+1}`).value = pacienteData.domicilio.provincia;
                        }catch(error){
                            console.log(error.message);
                        }
                    });

                    btnGuardarCambios.addEventListener('click', async () => {
                        const idPaciente = parseInt(id);
                        const nombre = document.getElementById(`nombre${index+1}`).value;
                        const apellido = document.getElementById(`apellido${index+1}`).value;
                        const dni = parseInt(document.getElementById(`dni${index+1}`).value);
                        const fechaIngreso = document.getElementById(`fechaIngreso${index+1}`).value;
                        const calle = document.getElementById(`calle${index+1}`).value;
                        const numero = document.getElementById(`numero${index+1}`).value;
                        const localidad = document.getElementById(`localidad${index+1}`).value;
                        const provincia = document.getElementById(`provincia${index+1}`).value;

                        // Crear un objeto con los datos a enviar
                        const PacienteModificado = {
                            id : parseInt(id),
                            nombre : nombre,
                            apellido: apellido,
                            dni: dni,
                            fechaIngreso: fechaIngreso,
                            calle: calle,
                            numero: numero,
                            localidad: localidad,
                            provincia: provincia
                        };
                        console.log(id);
                        console.log(PacienteModificado);

                        try{
                            const urlArtualizar = URL_BASE+'/pacientes/actualizar';
                            const respuesta = await actualizardatos(urlArtualizar, PacienteModificado);
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
                                        const urlDelete = URL_BASE+'/pacientes/eliminar/'+id;
                                        
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

        }catch(error){
            console.log(error.message)
        }
    });
})();