/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


   package examen.prog2.entities;

import examen.prog2.enums.Rol;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String direccion;
    private String contrasenia;
    private Rol rol;
    private final List<Pedido> pedidos;

    // Constructor: el id se asigna automáticamente desde Base
    public Usuario(String nombre, String apellido, String mail,
                   String celular, String contrasenia, Rol rol) {
        super(); // llama al constructor de Base que asigna id único

        this.nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : "Nombre invalido";
        this.apellido = (apellido != null && !apellido.trim().isEmpty()) ? apellido : "Apellido invalido";
        this.mail = (mail != null && !mail.trim().isEmpty()) ? mail : "Mail invalido";
        this.celular = (celular != null && !celular.trim().isEmpty()) ? celular : "Sin celular";
        this.direccion = direccion;
        this.contrasenia = (contrasenia != null && !contrasenia.trim().isEmpty()) ? contrasenia : "1234";
        this.rol = (rol != null) ? rol : Rol.USUARIO;
        this.pedidos = new ArrayList<>();
    }

    // Relación bidireccional con Pedido
    public void agregarPedido(Pedido pedido) {
        if (pedido != null && !pedidos.contains(pedido)) {
            pedidos.add(pedido);
        }
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : "Nombre invalido";
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {
        this.apellido = (apellido != null && !apellido.trim().isEmpty()) ? apellido : "Apellido invalido";
    }

    public String getMail() { return mail; }
    public void setMail(String mail) {
        this.mail = (mail != null && !mail.trim().isEmpty()) ? mail : "Mail invalido";
    }

    public String getCelular() { return celular; }
    public void setCelular(String celular) {
        this.celular = (celular != null && !celular.trim().isEmpty()) ? celular : "Sin celular";
    }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = (contrasenia != null && !contrasenia.trim().isEmpty()) ? contrasenia : "1234";
    }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = (rol != null) ? rol : Rol.USUARIO; }

    public List<Pedido> getPedidos() { return pedidos; }

    // toString
@Override
public String toString() {
    return "ID: " + getId() + " |USUARIO: " + nombre + " " + apellido +
           " | Mail: " + mail +
           " | Rol: " + rol;
}

// equals y hashCode
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Usuario)) return false;
    Usuario usuario = (Usuario) o;
    return Objects.equals(getId(), usuario.getId()) &&
            Objects.equals(mail, usuario.mail);
}

@Override
public int hashCode() {
    return Objects.hash(getId(), mail);
}

    public void setDireccion(String direccion) {
    this.direccion = (direccion != null && !direccion.trim().isEmpty()) ? direccion : "Sin direccion";
}

public void setTelefono(String telefono) {
    this.celular = (telefono != null && !telefono.trim().isEmpty()) ? telefono : "Sin celular";
}

}
