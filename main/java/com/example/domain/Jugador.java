package com.example.domain;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String fechaNacimiento;
    private int numCanastasTotales;
    private int numAsistenciasTotales;
    private int numRebotesTotales;
    private String posicion;
    @ManyToOne  //un jugador sólo puede pertenecer a un equipo
    private Equipo equipo;

    public Jugador() {
    }

    public Jugador(String nombre, String fechaNacimiento, int numCanastasTotales, int numAsistenciasTotales, int numRebotesTotales, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.numCanastasTotales = numCanastasTotales;
        this.numAsistenciasTotales = numAsistenciasTotales;
        this.numRebotesTotales = numRebotesTotales;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getNumCanastasTotales() {
        return numCanastasTotales;
    }

    public int getNumAsistenciasTotales() {
        return numAsistenciasTotales;
    }

    public int getNumRebotesTotales() {
        return numRebotesTotales;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNumCanastasTotales(int numCanastasTotales) {
        this.numCanastasTotales = numCanastasTotales;
    }

    public void setNumAsistenciasTotales(int numAsistenciasTotales) {
        this.numAsistenciasTotales = numAsistenciasTotales;
    }

    public void setNumRebotesTotales(int numRebotesTotales) {
        this.numRebotesTotales = numRebotesTotales;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", numCanastasTotales=" + numCanastasTotales +
                ", numAsistenciasTotales=" + numAsistenciasTotales +
                ", numRebotesTotales=" + numRebotesTotales +
                ", posicion='" + posicion + '\'' +
                '}';
    }
}