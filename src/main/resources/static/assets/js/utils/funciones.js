export const URL_BASE = "http://localhost:8080";

export function mostrarMensaje(configuracion) {
    Swal.fire(configuracion).then(() => {
        // Refrescar la página
        location.reload();
    });
}

export async function enviarDatosGet(url) {
    const response = await fetch(url, {
        method: 'GET'
    });

    const data = await response.json();

    return data;
}
export async function enviarDatosPost(url, datos) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
        
    const data = await response.json();
    
    if (response.status != 201) {
        let mensaje = "";
        if (data !== undefined && data !== null) {
            if(response.status < 500){
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

export async function actualizardatos(url, datos) {
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const data = await response.json();

    if (response.status != 200) {
        let mensaje = "";
        if (data !== undefined && data !== null) {
            if(response.status < 500){
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

export async function eliminarRegistro(url) {
    const response = await fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
    });

    const data = response;
    
    if (response.status > 300) {
        let mensaje = "";
        if (data !== undefined && data !== null) {
            if(response.status < 500){

                mensaje = data;
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