package com.backend.digitalhouse.dao;

import java.util.List;

public interface IDao<T>{
    //alta, buscarlos, eliminarlos y listarlos
    T registrar(T t);
    T buscarPorId(int id);
    void eliminar(int id);
    List<T> listarTodos();

}
