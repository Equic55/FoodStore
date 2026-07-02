/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen.prog2.services;

import examen.prog2.entities.Producto;
import examen.prog2.entities.Categoria;
import examen.prog2.exceptions.DatoInvalidoException;
import examen.prog2.exceptions.StockInvalidoException;
import examen.prog2.exceptions.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoService {

    private final List<Producto> productos = new ArrayList<>();

    // Crear producto
    public void crearProducto(String nombre, Double precio, String descripcion, Integer stock,
                              String imagen, Boolean disponible, Categoria categoria) throws DatoInvalidoException, StockInvalidoException {
        if (nombre == null || nombre.isBlank()) {
            throw new DatoInvalidoException("El nombre del producto no puede estar vacio");
        }
        if (precio == null || precio <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor a 0");
        }
        if (stock == null || stock < 0) {
            throw new StockInvalidoException("El stock no puede ser negativo");
        }
        Producto producto = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoria);
        productos.add(producto);
        System.out.println("Producto creado con exito: " + producto);
    }

    // Listar productos por categoría
    public List<Producto> listarProductosPorCategoria(Long catId) {
        return productos.stream()
                .filter(p -> !p.isEliminado()
                          && p.getCategoria() != null
                          && p.getCategoria().getId().equals(catId))
                .collect(Collectors.toList());
    }

    // Buscar producto por ID con nueva excepción
    public Producto buscarPorId(Long id) throws EntidadNoEncontradaException {
        return productos.stream()
                .filter(p -> p.getId().equals(id) && !p.isEliminado())
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("Producto con ID " + id + " no encontrado"));
    }

    // Modificar producto
    public void modificarProducto(Long id, String nuevoNombre, Double nuevoPrecio, String nuevaDescripcion,
                                  Integer nuevoStock, String nuevaImagen, Boolean disponible, Categoria nuevaCategoria)
            throws EntidadNoEncontradaException, DatoInvalidoException, StockInvalidoException {
        Producto producto = buscarPorId(id);

        if (nuevoNombre != null) producto.setNombre(nuevoNombre);
        if (nuevoPrecio != null && nuevoPrecio > 0) producto.setPrecio(nuevoPrecio);
        if (nuevaDescripcion != null) producto.setDescripcion(nuevaDescripcion);
        if (nuevoStock != null && nuevoStock >= 0) producto.setStock(nuevoStock);
        if (nuevaImagen != null) producto.setImagen(nuevaImagen);
        if (disponible != null) producto.setDisponible(disponible);
        if (nuevaCategoria != null) producto.setCategoria(nuevaCategoria);

        System.out.println("Producto modificado: " + producto);
    }

    // Eliminar producto (soft delete)
    public void eliminarProducto(Long id) throws EntidadNoEncontradaException {
        Producto producto = buscarPorId(id);
        producto.setEliminado(true);
        System.out.println("Producto eliminado: " + producto);
    }

    // Listar todos los productos activos
    public List<Producto> listarProductos() {
        return productos.stream()
                .filter(p -> !p.isEliminado())
                .collect(Collectors.toList());
    }
}
