/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.prog2.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Categoria extends Base {

    private String nombre;
    private String descripcion;
    private final List<Producto> productos;
    private boolean eliminado = false; // NUEVO ATRIBUTO

    // Constructor
    public Categoria(String nombre, String descripcion) {
        super();
        this.nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : "Nombre invalido";
        this.descripcion = (descripcion != null && !descripcion.trim().isEmpty()) ? descripcion : "Sin descripcion";
        this.productos = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : "Nombre invalido";
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion != null && !descripcion.trim().isEmpty()) ? descripcion : "Sin descripcion";
    }

    public List<Producto> getProductos() { return productos; }

    
    @Override
    public boolean isEliminado() { return eliminado; }
    @Override
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion;   
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(getId(), categoria.getId()) &&
                Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), nombre);
    }
public void agregarProducto(Producto producto) {
    if (producto != null && !productos.contains(producto)) {
        productos.add(producto);
    }
}

}

