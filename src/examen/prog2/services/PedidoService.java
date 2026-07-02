/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.prog2.services;

import examen.prog2.entities.Pedido;
import examen.prog2.entities.Producto;
import examen.prog2.entities.Usuario;
import examen.prog2.entities.DetallePedido;
import examen.prog2.enums.Estado;
import examen.prog2.enums.FormaPago;
import examen.prog2.exceptions.DatoInvalidoException;
import examen.prog2.exceptions.EntidadNoEncontradaException;
import examen.prog2.exceptions.StockInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private final List<Pedido> pedidos = new ArrayList<>();

    public Pedido crearPedido(Usuario usuario, FormaPago formaPago, Estado estado) throws DatoInvalidoException {
        if (usuario == null || usuario.isEliminado()) {
            throw new DatoInvalidoException("El usuario no puede ser nulo o esta eliminado");
        }
        Pedido pedido = new Pedido(usuario, formaPago);
        pedido.setEstado(estado);
        pedidos.add(pedido);
        System.out.println("Pedido creado con exito. ID: " + pedido.getId());
        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return pedidos.stream().filter(p -> !p.isEliminado()).toList();
    }

    public Pedido buscarPorId(Long id) throws EntidadNoEncontradaException {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id) && !p.isEliminado())
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("Pedido no encontrado con ID: " + id));
    }

    public void agregarProductoAPedido(Long idPedido, Producto producto, int cantidad)
            throws EntidadNoEncontradaException, StockInvalidoException {
        Pedido pedido = buscarPorId(idPedido);
        pedido.addDetallePedido(producto, cantidad);
        System.out.println("Producto agregado al pedido: " + producto.getNombre());
    }

    public void modificarPedido(Long idPedido, Estado nuevoEstado, FormaPago nuevaFormaPago) throws EntidadNoEncontradaException {
        Pedido pedido = buscarPorId(idPedido);
        pedido.setEstado(nuevoEstado);
        pedido.setFormaPago(nuevaFormaPago);
        System.out.println("Pedido actualizado: " + pedido);
    }

    public void eliminarPedido(Long idPedido) throws EntidadNoEncontradaException {
        Pedido pedido = buscarPorId(idPedido);
        pedido.setEliminado(true);

       
        pedido.getDetalles().forEach(d -> d.setEliminado(true));

        System.out.println("Pedido eliminado: " + pedido);
    }

    public void cancelarPedido(Long idPedido) throws EntidadNoEncontradaException {
        Pedido pedido = buscarPorId(idPedido);
        pedidos.remove(pedido);
        System.out.println("Pedido cancelado por error en detalle.");
    }

    public List<Pedido> listarPedidosPorUsuario(Long idUsuario) {
        return pedidos.stream()
                .filter(p -> !p.isEliminado() && p.getUsuario() != null && p.getUsuario().getId().equals(idUsuario))
                .toList();
    }
}

