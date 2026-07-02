/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.prog2.services;

import examen.prog2.entities.Usuario;
import examen.prog2.enums.Rol;
import examen.prog2.exceptions.DatoInvalidoException;
import examen.prog2.exceptions.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    // Crear usuario con validación de mail único
 public void crearUsuario(String nombre, String apellido, String mail, String telefono, String direccion, Rol rol)
        throws DatoInvalidoException {
    if (mail == null || mail.isBlank()) {
        throw new DatoInvalidoException("El mail no puede estar vacio");
    }
    if (usuarios.stream().anyMatch(u -> u.getMail().equalsIgnoreCase(mail) && !u.isEliminado())) {
        throw new DatoInvalidoException("El mail ya esta registrado");
    }
    Usuario usuario = new Usuario(nombre, apellido, mail, telefono, direccion, rol);
    usuarios.add(usuario);
    System.out.println("Usuario creado con exito: " + usuario);
}


    // Listar usuarios activos
    public List<Usuario> listarUsuarios() {
        return usuarios.stream()
                .filter(u -> !u.isEliminado())
                .collect(Collectors.toList()); // ✅ corregido
    }

    // Buscar usuario por ID con nueva excepción
    public Usuario buscarPorId(Long id) throws EntidadNoEncontradaException {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id) && !u.isEliminado())
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaException("Usuario con ID " + id + " no encontrado"));
    }

    // Modificar usuario
    public void modificarUsuario(Long id, String nuevoNombre, String nuevoApellido, String nuevoMail,
                                 String nuevoTelefono, String nuevaDireccion, Rol nuevoRol) throws EntidadNoEncontradaException {
        Usuario usuario = buscarPorId(id);
        if (nuevoNombre != null) usuario.setNombre(nuevoNombre);
        if (nuevoApellido != null) usuario.setApellido(nuevoApellido);
        if (nuevoMail != null) usuario.setMail(nuevoMail);
        if (nuevoTelefono != null) usuario.setTelefono(nuevoTelefono);
        if (nuevaDireccion != null) usuario.setDireccion(nuevaDireccion);
        if (nuevoRol != null) usuario.setRol(nuevoRol);
        System.out.println("Usuario modificado: " + usuario);
    }

    // Eliminar usuario (soft delete)
    public void eliminarUsuario(Long id) throws EntidadNoEncontradaException {
        Usuario usuario = buscarPorId(id);
        usuario.setEliminado(true);
        System.out.println("Usuario eliminado: " + usuario);
    }

    private static class direccion {

        public direccion() {
        }
    }
}
