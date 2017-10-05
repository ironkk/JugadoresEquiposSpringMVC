package com.example.repository;

import com.example.domain.Equipo;
import com.example.domain.Jugador;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    /*
        a.	Consulta los equipos existentes en una localidad determinada.
        b.	Devuelve todos los jugadores de un equipo, a partir del nombre completo del equipo.
        c.	Devuelve todos los jugadores de un equipo, que además jueguen en la misma posición (parámetro adicional de la consulta), por ejemplo, alero.
        d.	Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.
     */
    List<Equipo> findByLocalidad(String localidad);

    Equipo findByNombre(String nombre);
    @Query("SELECT jugador from Jugador jugador WHERE jugador.equipo.nombre is :nombre")
    List<Jugador> findJugadoresByEquipo(@Param("nombre") String nombre);

    @Query("SELECT jugador from Jugador jugador WHERE jugador.equipo.nombre is :nombre and jugador.posicion is :posicion")
    List<Jugador> findJugadoresByEquipoAndPosicion(@Param("nombre") String nombre, @Param("posicion") String posicion);

    @Query("SELECT  jugador " +"FROM Jugador jugador "
            +"WHERE jugador.equipo.nombre=:nombreEquipo "
            +"ORDER BY jugador.numCanastasTotales DESC")
    Page<Jugador> JugadorMaxCanastasEquipo(@Param("nombreEquipo") String nombreEquipo, Pageable pageable);

}

