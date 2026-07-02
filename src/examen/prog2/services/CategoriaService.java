/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.prog2.services;

import examen.prog2.entities.Categoria;
import examen.prog2.exceptions.DatoInvalidoException;
import examen.prog2.exceptions.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService {

    private final List<Categoria> categorias = new ArrayList<>();

    // Crear categoría
   public void crearCategoria(String nombre, String descripcion) throws DatoInvalidoException {
    if (nombre == null || nombre.isBlank()) {
        throw new DatoInvalidoException("El nombre de la categoria no puede estar vacio");
    }

    // Validar que no exista otra categoria con el mismo nombre
    for (Categoria c : categorias) {
        if (c.getNombre().equalsIgnoreCase(nombre)) {
            System.out.println("Error: ya existe una categoria con el nombre '" + nombre + "'");
            return; // no se agrega
        }
    }

    // Crear y agregar categoria
    Categoria categoria = new Categoria(nombre, descripcion);
    categorias.add(categoria);

    // Mostrar id generado
    System.out.println("Categoria creada con exito. ID generado: " + categoria.getId());
}


    // Listar categorías activas (no eliminadas)
    public List<Categoria> listarCategorias() {
    return categorias.stream()
            .filter(c -> !c.isEliminado())
            .toList();
}

    // Buscar categoría por ID con nueva excepción
    public Categoria buscarPorId(Long id) throws EntidadNoEncontradaException {
        return categorias.stream()
                .filter(c -> c.getId().equals(id) && !c.isEliminado())
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("Categoria con ID " + id + " no encontrada"));
    }

    // Modificar categoría
    public void modificarCategoria(Long id, String nuevoNombre, String nuevaDescripcion) throws EntidadNoEncontradaException {
        Categoria categoria = buscarPorId(id);
        if (nuevoNombre != null) categoria.setNombre(nuevoNombre);
        if (nuevaDescripcion != null) categoria.setDescripcion(nuevaDescripcion);
        System.out.println("Categoria modificada: " + categoria);
    }

// Eliminar categoría (soft delete)
public void eliminarCategoria(Long id) throws DatoInvalidoException {
    Categoria categoria = categorias.stream()
            .filter(c -> c.getId().equals(id) && !c.isEliminado())
            .findFirst()
            .orElse(null);

    if (categoria == null) {
        System.out.println("Error: categoria no encontrada o ya eliminada");
        return;
    }

    // Validar productos asociados
    boolean tieneProductosActivos = categoria.getProductos().stream()
            .anyMatch(p -> !p.isEliminado() && p.isDisponible());

    if (tieneProductosActivos) {
        System.out.println("No se puede eliminar la categoria porque contiene productos activos");
        return;
    }
    
    categoria.setEliminado(true);
    System.out.println("Categoria eliminada: " + categoria);
}

}
