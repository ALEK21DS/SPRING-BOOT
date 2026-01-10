package com.krakedev.inventario.service;

import java.util.List;
import java.util.Optional;

import com.krakedev.inventario.entity.EstadoProducto;
import com.krakedev.inventario.entity.Producto;

public interface ProductoService {
    Producto registraProducto(Producto producto);
    List<Producto> listarProducto();
    Optional<Producto> buscarPorNombre(String nombre);
    Optional<Producto> buscarPorId(Long idProducto);
    Producto actualizaProducto(Long idProducto, Producto producto);

    void eliminarProducto(Long idProducto);

    Producto cambiarEstaProducto(Long idProducto, EstadoProducto nuevoEstadoProducto);
    List<Producto> obtenerPorEstadoProductos(EstadoProducto estadoProducto);
}
