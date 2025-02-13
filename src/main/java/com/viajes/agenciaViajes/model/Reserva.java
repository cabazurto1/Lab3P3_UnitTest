package com.viajes.agenciaViajes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación ManyToOne: muchas reservas pueden ser de un solo usuario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relación ManyToOne: muchas reservas pueden referirse a un mismo viaje
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "viaje_id", nullable = false)
    private Viaje viaje;

    @Column(nullable = false)
    private LocalDateTime fechaReserva;

    // Ejemplo: "RESERVADO", "CANCELADO", "COMPLETADO", etc.
    @Column(nullable = false)
    private String estado;

    // Precio final pagado al hacer la reserva (puede ser igual o distinto al precio del viaje)
    private double precioTotal;

    // Constructor vacío (obligatorio para JPA)
    public Reserva() {
    }

    // Constructor con parámetros
    public Reserva(int id, Usuario usuario, Viaje viaje,
                   LocalDateTime fechaReserva, String estado, double precioTotal) {
        this.id = id;
        this.usuario = usuario;
        this.viaje = viaje;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.precioTotal = precioTotal;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
