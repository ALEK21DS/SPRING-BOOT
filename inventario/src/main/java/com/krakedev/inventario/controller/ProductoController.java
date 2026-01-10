package com.krakedev.inventario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.inventario.entity.EstadoProducto;
import com.krakedev.inventario.entity.Producto;
import com.krakedev.inventario.service.ProductoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.registraProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProducto() {
        List<Producto> productos = productoService.listarProducto();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        Optional<Producto> producto = productoService.buscarPorNombre(nombre);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto productoActualizado = new Producto();
            productoActualizado.setNombreProducto(producto.getNombreProducto());
            productoActualizado.setDescripcion(producto.getDescripcion());
            productoActualizado.setCantidad(producto.getCantidad());
            productoActualizado.setPrecio(producto.getPrecio());
            productoActualizado.setEstadoProducto(producto.getEstadoProducto());

            Producto productoBDD = productoService.actualizaProducto(id, productoActualizado);

            return ResponseEntity.ok(productoBDD);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long id, @RequestBody EstadoProducto estadoProducto) {
        try{
            Producto producto = productoService.cambiarEstaProducto(id, estadoProducto);
            return ResponseEntity.ok(producto);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/estado/{estadoProducto}")
    public ResponseEntity<List<Producto>> listarProductosPorEstado(@PathVariable EstadoProducto estadoProducto) {
        List<Producto> productos = productoService.obtenerPorEstadoProductos(estadoProducto);
        return ResponseEntity.ok(productos);
    }

}
