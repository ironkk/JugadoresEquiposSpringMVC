package com.example.services;

import com.example.domain.Equipo;
import com.example.domain.Jugador;
import com.example.repository.EquipoRepository;
import com.example.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    public void testJugadores(){

        //Creacion de objetos jugador y equipo
        Equipo e1 = new Equipo("FCB", "Barcelona", "01/01/2001");
        equipoRepository.save(e1);
        Equipo e2 = new Equipo("FCBE", "Sevilla", "25/05/1962");
        equipoRepository.save(e2);
        Equipo e3 = new Equipo("Alele", "Barcelona", "9/7/2002");
        equipoRepository.save(e3);
        Equipo e4 = new Equipo("CAT", "Barcelona", "16/11/2014");
        equipoRepository.save(e4);
        Equipo e5 = new Equipo("ESP", "Madrid", "22/11/2011");
        equipoRepository.save(e5);

        Jugador j1 = new Jugador("Javier", "22/10/1992", 25, 10, 1, "Base", e1);
        jugadorRepository.save(j1);
        Jugador j2 = new Jugador("Javi", "09/11/1995", 15, 10, 0, "Escolta",e1);
        jugadorRepository.save(j2);
        Jugador j3 = new Jugador("Javito", "07/06/2003", 2, 8, 10, "Base",e1);
        jugadorRepository.save(j3);

        Jugador j4 = new Jugador("Manzano", "13/07/1988", 23, 69, 10, "Alero", e2);
        jugadorRepository.save(j4);
        Jugador j5 = new Jugador("Manzi", "21/05/1994", 300, 36, 10, "Alero",e3);
        jugadorRepository.save(j5);
        Jugador j6 = new Jugador("Manzanito", "27/07/1997", 200, 100, 59, "Pivot",e4);
        jugadorRepository.save(j6);


        //Querys jugador y equipo

        //Querys Equipo
        System.out.println("*********************************************");
        System.out.println("PRUEBAS PARA EQUIPOS");
        System.out.println("*********************************************");

        System.out.println("Equipos de Barcelona: ");
        System.out.println(equipoRepository.findByLocalidad("Barcelona"));

        System.out.println("Jugadores del equipo 'FCB' : ");
        System.out.println(equipoRepository.findJugadoresByEquipo("FCB"));
        System.out.println("Bases del equipo 'FCB' : ");
        System.out.println(equipoRepository.findJugadoresByEquipoAndPosicion("FCB", "Base"));
        System.out.println("Maximo anotador del 'FCB' : ");
        Pageable topone = new PageRequest(0,1);
        //https://docs.oracle.com/javase/7/docs/api/java/awt/print/Pageable.html
        showStatisticsBestPlayer(equipoRepository.JugadorMaxCanastasEquipo("FCB", topone));


        // QUERYS JUGADOR
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("*********************************************");
        System.out.println("PRUEBAS PARA JUGADORES");
        System.out.println("*********************************************");


        System.out.println("Jugadores nombre como Jav: ");
        System.out.println(jugadorRepository.findByNombreContaining("Jav"));

        System.out.println("Jugadores canastas > 20: ");
        System.out.println(jugadorRepository.findByNumCanastasTotalesGreaterThanEqual(20));

        System.out.println("Asistencias totales entre 1 y 10: ");
        System.out.println(jugadorRepository.findByNumAsistenciasTotalesBetween(1, 10));

        System.out.println("Jugadores posicion BASE: ");
        System.out.println(jugadorRepository.findByPosicionIs("Base"));

        System.out.println("Fecha nacimiento > 02/02/1995");
        System.out.println(jugadorRepository.findByFechaNacimientoGreaterThanEqual("02/02/1995"));

        System.out.println("Media canastas, asistencias, rebotes");
        //System.out.println(jugadorRepository.AvgNumCanastasTotalesAndNumAsistenciasTotalesAndNumRebotesTotalesPerPosicion());
        jugadorRepository.AvgCanastasAsistenciasRebotesPorPosicion().forEach(estadisticasPosicion -> {
            System.out.println(estadisticasPosicion[0]+" => Media canastas: "+estadisticasPosicion[1]
                    +", media asistencias: "+estadisticasPosicion[2]+", media rebotes: "+estadisticasPosicion[3]);
        });

        System.out.println("Media , min y max de canastas, asistencias, rebotes");
        //System.out.println(jugadorRepository.AvgAndMaxAndMinNumCanastasTotalesAndNumAsistenciasTotalesAndNumRebotesTotalesPerPosicion());
        jugadorRepository.
                AvgMaxMinCanastasAsistenciasRebotesPorPosicion().
                forEach(estadisticasPosicion -> {
                    System.out.println("Posicion: " + estadisticasPosicion[0]);
                    System.out.print("Maximo canastas: " + estadisticasPosicion[1]);
                    System.out.print(", minimo canastas: "+estadisticasPosicion[2]);
                    System.out.println(", media Canastas: "+estadisticasPosicion[3]);
                    System.out.print("Maximo asistencias: " + estadisticasPosicion[4]);
                    System.out.print(", minimo asistencias: "+estadisticasPosicion[5]);
                    System.out.println(", media asistencias: "+estadisticasPosicion[6]);
                    System.out.print("Maximo rebotes: " + estadisticasPosicion[7]);
                    System.out.print(", minimo rebotes: "+estadisticasPosicion[8]);
                    System.out.println(", media rebotes: "+estadisticasPosicion[9]);
                });
        List<Jugador> lista =jugadorRepository.getJugadoresOrderBy(new Sort(Sort.Direction.DESC, "numCanastasTotales"));
        System.out.println(lista);
    }
    private void showStatisticsBestPlayer(Page<Jugador> jugadorPage){
        jugadorPage.getContent().forEach(jugador -> {
            System.out.println(jugador);
        });
    }
}

