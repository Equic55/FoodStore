package examen.prog2.entities;

import java.util.Objects;

public class Producto extends Base {

    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;

    // Constructor el id se asigna  desde Base
    public Producto(String nombre, double precio, String descripcion,
                    int stock, String imagen, boolean disponible, Categoria categoria) {
        super(); // llama al constructor de Base que asigna id único

        this.nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : "Nombre invalido";
        this.precio = (precio > 0) ? precio : 0.0;
        this.descripcion = (descripcion != null && !descripcion.trim().isEmpty()) ? descripcion : "Sin descripcion";
        this.stock = (stock >= 0) ? stock : 0;
        this.imagen = (imagen != null && !imagen.trim().isEmpty()) ? imagen : "Sin imagen";
        this.disponible = disponible;
        this.categoria = categoria;

        // Relación bidireccional: si tiene categoría, se agrega automáticamente
        if (categoria != null) {
            categoria.agregarProducto(this);
        }
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : "Nombre invalido";
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        this.precio = (precio > 0) ? precio : 0.0;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion != null && !descripcion.trim().isEmpty()) ? descripcion : "Sin descripción";
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        this.stock = (stock >= 0) ? stock : 0;
    }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) {
        this.imagen = (imagen != null && !imagen.trim().isEmpty()) ? imagen : "Sin imagen";
    }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        if (categoria != null) {
            categoria.agregarProducto(this);
        }
    }

    // toString
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "Sin categoría") +
                '}';
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(getId(), producto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
