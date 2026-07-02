/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen.prog2.entities;

import examen.prog2.enums.Estado;
import examen.prog2.enums.FormaPago;
import examen.prog2.interfaces.Calculable;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido extends Base implements Calculable {

    private Usuario usuario;
    private FormaPago formaPago;
    private Estado estado;
    private final  LocalDate fecha;
    private final List<DetallePedido> detalles;
    private double total;

    // Constructor
    public Pedido(Usuario usuario, FormaPago formaPago) {
        super(); 
        this.usuario = usuario;
        this.formaPago = (formaPago != null) ? formaPago : FormaPago.EFECTIVO;
        this.estado = Estado.PENDIENTE;
        this.fecha = java.time.LocalDate.now();
        this.detalles = new ArrayList<>();
        this.total = 0.0;

        if (usuario != null) {
            usuario.agregarPedido(this);
        }
    }

    // Método obligatorio del UML
   public void addDetallePedido(Producto producto, int cantidad) {
    if (producto != null && cantidad > 0 && producto.getPrecio() > 0) {
        DetallePedido detalle = new DetallePedido(this, producto, cantidad);
        detalles.add(detalle);
        calcularTotal();
    }
}


    // Buscar detalle por producto
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        return detalles.stream()
                .filter(d -> d.getProducto().equals(producto))
                .findFirst()
                .orElse(null);
    }

    // Eliminar detalle por producto
    public void deleteDetallePedidoByProducto(Producto producto) {
        detalles.removeIf(d -> d.getProducto().equals(producto));
        calcularTotal();
    }

    // Implementación de la interfaz Calculable
    @Override
    public void calcularTotal() {
        total = detalles.stream().mapToDouble(DetallePedido::calcularSubtotal).sum();
    }

    // Getters y Setters
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public List<DetallePedido> getDetalles() { return detalles; }

    public double getTotal() { return total; }

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("===============================================================\n");
    sb.append("USUARIO: ").append(usuario.getNombre()).append(" ").append(usuario.getApellido())
      .append(" | Mail: ").append(usuario.getMail())
      .append(" | Rol: ").append(usuario.getRol()).append("\n");
    sb.append("================================================================\n");
    sb.append("Pedido #").append(getId())
      .append(" | Fecha: ").append(" | Fecha: ").append(fecha)  // usa la fecha actual
      .append(" | Estado: ").append(estado)
      .append(" | FormaPago: ").append(formaPago).append("\n\n");

    for (DetallePedido d : detalles) {
        sb.append(d.toString()).append("\n");
    }

   
    // En un sistema real se acumularian varios pedidos del mismo usuario
    sb.append("TOTAL ACUMULADO del usuario: $").append(total).append("\n");
    sb.append("================================================================\n");

    return sb.toString();
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pedido)) return false;
    Pedido pedido = (Pedido) o;
    return Objects.equals(getId(), pedido.getId());
}

@Override
public int hashCode() {
    return Objects.hash(getId());
}

    public String getFecha() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
