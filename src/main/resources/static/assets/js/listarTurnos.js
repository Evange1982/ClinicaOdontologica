import { URL_BASE, mostrarMensaje, enviarDatosGet, actualizardatos, eliminarRegistro} from "./utils/funciones.js";
(function() {
    "use strict";
    document.addEventListener("DOMContentLoaded", async function() {
        const url = URL_BASE + '/turnos/';
        const tabla = document.querySelector("#tablaTurnos");
        let contenidoTabla = '';
        console.log("Hola mundo");
        try{
            const listaturnos = await enviarDatosGet(url);
            let numeroItem = 1;
            listaturnos.forEach(turno => {
                contenidoTabla += `<tr>
                <th scope="row">${numeroItem}</th>
                <td>${turno.odontologoTurnoSalidaDto.nombre}</td>
                <td>${turno.pacienteTurnoSalidaDto.nombre}</td>
                <td>${turno.fechaYHora}</td>
                <td>
                    <button type="button" class="btn btn-danger" id="eliminar${numeroItem}" data-index="${turno.id}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                </td>
                </tr>`
                numeroItem++;
            });

            if (listaturnos.length >0){
                tabla.innerHTML = contenidoTabla;   
                listaturnos.forEach((turno, index) => {
                const btnEliminar = document.querySelector(`#eliminar${index + 1}`);
                const id = btnEliminar.getAttribute('data-index');

                btnEliminar.addEventListener("click", async () => {
                    console.log(id);
                    if (id !== null) {
                        const swalWithBootstrapButtons = Swal.mixin({customClass: {confirmButton: 'btn btn-success',cancelButton: 'btn btn-danger'},
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
                                        const urlDelete = URL_BASE+'/turnos/eliminar/'+id;
                                        
                                        const estaEliminado = await eliminarRegistro(urlDelete);
                                        
                                        swalWithBootstrapButtons.fire(
                                            'Eliminado!',
                                            'El turno a sido eliminado.',
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
                tabla.innerHTML = '<tr><td colspan="5">No hay Turnos para mostrar</td></tr>';
            }
        }catch(error){
            const mensaje = error.message;
            console.log(mensaje);
            mostrarMensaje({icon:'error',title: 'ERROR',text : mensaje});
        }
    });
})();