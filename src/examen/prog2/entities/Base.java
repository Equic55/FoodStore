/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen.prog2.entities;

import java.time.LocalDateTime;

public abstract class Base {

    private static long contador = 1; // contador global para IDs únicos
    private final Long id;            // id único de cada entidad
    private boolean eliminado;        // baja lógica
    private final LocalDateTime createdAt; // fecha de creación

    // Constructor automático: asigna id único a cada objeto
    public Base() {
        this.id = contador++;
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getter del id
    public Long getId() {
        return id;
    }

    // Getter y setter de eliminado
    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    // Getter de fecha de creación
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Método abstracto: obliga a las clases hijas a implementar toString
    @Override
    public abstract String toString();
}
