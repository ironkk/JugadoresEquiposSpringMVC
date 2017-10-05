package com.example.repository;

import com.example.domain.Jugador;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;



public interface JugadorRepository extends JpaRepository<Jugador, Long>{
    List<Jugador> findByNombreContaining(String nombre);
    List<Jugador> findByNumCanastasTotalesGreaterThanEqual(int numCanastas);
    List<Jugador> findByNumAsistenciasTotalesBetween(int a, int b);
    List<Jugador> findByPosicionIs(String posicion);
    List<Jugador> findByFechaNacimientoGreaterThanEqual(String fecha);
    List<Jugador> findByOrderByNumCanastasTotalesDesc();
    List<Jugador> findByNumCanastasTotalesBetween(int min, int max);


    @Query("SELECT jugador.posicion, AVG(jugador.numCanastasTotales), AVG(jugador.numAsistenciasTotales), AVG(jugador.numRebotesTotales) " +
            "FROM Jugador jugador " +
            "GROUP BY jugador.posicion")
    List<Object[]> AvgCanastasAsistenciasRebotesPorPosicion();

    @Query("SELECT jugador.posicion, MAX(jugador.numCanastasTotales), MIN(jugador.numCanastasTotales),AVG(jugador.numCanastasTotales), " +
            "MAX(jugador.numAsistenciasTotales), MIN(jugador.numAsistenciasTotales), AVG(jugador.numAsistenciasTotales), " +
            "MAX(jugador.numRebotesTotales), MIN(jugador.numRebotesTotales), AVG(jugador.numRebotesTotales) " +
            "FROM Jugador jugador " +
            "GROUP BY jugador.posicion")
    List<Object[]> AvgMaxMinCanastasAsistenciasRebotesPorPosicion();

    @Query("Select jugador from Jugador jugador")
    List<Jugador> getJugadoresOrderBy(Sort sort);

    @Query("SELECT jugador.posicion, MAX(jugador.numCanastasTotales), MIN(jugador.numCanastasTotales),AVG(jugador.numCanastasTotales)" +
            "FROM Jugador jugador " +
            "GROUP BY jugador.posicion")
    List<Object[]> AvgMaxMinCanastas();
}