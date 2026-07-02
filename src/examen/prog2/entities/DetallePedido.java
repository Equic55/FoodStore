/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen.prog2.entities;

import java.util.Objects;

public class DetallePedido extends Base {

    private Pedido pedido;
    private Producto producto;
    private int cantidad;
    private boolean eliminado = false; // ✅ nuevo atributo para baja lógica

    // Constructor principal
    public DetallePedido(Pedido pedido, Producto producto, int cantidad) {
        super(); // id unico automatico desde Base
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = (cantidad > 0) ? cantidad : 1;

        // Relacion bidireccional: el pedido agrega este detalle
        if (pedido != null && !pedido.getDetalles().contains(this)) {
            pedido.getDetalles().add(this);
        }
    }

    // Getters y Setters
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = (cantidad > 0) ? cantidad : 1;
    }

    @Override
    public boolean isEliminado() { return eliminado; }
    @Override
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    // Subtotal calculado dinamicamente
    public double calcularSubtotal() {
        return (producto != null) ? producto.getPrecio() * cantidad : 0.0;
    }

    // toString descriptivo
    @Override
    public String toString() {
        return "DetallePedido #" + getId() +
               " | Producto: " + (producto != null ? producto.getNombre() : "Sin producto") +
               " | Cantidad: " + cantidad +
               " | Subtotal: $" + calcularSubtotal() +
               (eliminado ? " | ELIMINADO" : "");
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetallePedido)) return false;
        DetallePedido that = (DetallePedido) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
