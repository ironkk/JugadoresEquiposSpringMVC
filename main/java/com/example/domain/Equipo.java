package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String localidad;
    private String fechaCreacion;

    public Equipo() {
    }

    public Equipo(String nombre, String localidad, String fechaCreacion) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.fechaCreacion = fechaCreacion;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", Localidad='" + localidad + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}