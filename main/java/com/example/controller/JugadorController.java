package com.example.controller;

import com.example.domain.Jugador;
import com.example.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/*
cd rest
mvn clean spring-boot:run
 */

/*
anotaciones para métodos HTTP
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@PatchMapping
 */

//1º a añadir
@RestController

// 2º a añadir -> url a localizar
@RequestMapping("/jugadores")
public class JugadorController {
    @Autowired
    private JugadorRepository jugadorRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Jugador crearJugador(@RequestBody Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @PutMapping
    public Jugador modificarJugador(@RequestBody Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    @GetMapping
    public List<Jugador> getJugadores() {
        return jugadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Jugador getJugadorPorId(@PathVariable Long id) {
        Jugador jugador = jugadorRepository.findOne(id);
        return jugador;
    }

    @DeleteMapping("/{id}")
    public void eliminarJugador(@PathVariable Long id) {
        jugadorRepository.delete(id);
    }

    //====>PRACTICA<======
    @GetMapping("/orderBy")
    public List<Jugador> getJugadoresOrdenadosPor(@RequestParam("orderBy") String orderBy){
        //return jugadorRepository.findByOrderByNumCanastasTotalesDesc();
        return jugadorRepository.getJugadoresOrderBy(new Sort(Sort.Direction.DESC, orderBy));
    }

    @GetMapping("/canastas/mas-de/{num}")
    public List<Jugador> getAnotadoresMasDe(@PathVariable int num){
        return jugadorRepository.findByNumCanastasTotalesGreaterThanEqual(num);
    }

    @GetMapping("/canastas/mas-que")
    public List<Jugador> getAnotadoresMasQue(@RequestParam("num") int num){
        return jugadorRepository.findByNumCanastasTotalesGreaterThanEqual(num);
    }
    @GetMapping("/canastas/entre")
    public List<Jugador> getAnotadoresEntre(@RequestParam("min") int min,@RequestParam("max") int max){
        return jugadorRepository.findByNumCanastasTotalesBetween(min, max);
    }
    @GetMapping("/por-posicion")
    public Map<String, List<Jugador>> getJugadoresGroupByPosicion(){
        return jugadorRepository
                .findAll()
                .parallelStream()
                .collect(Collectors.groupingBy(Jugador::getPosicion));
    }


    /*Devolver los jugadores agrupados por posición mediante un Map. Para cada posición mostrar las
    siguientes estadísticas: mínimo, máximo y media del número de canastas (podéis experimentar con
    otros valores como, por ejemplo, asistencias).*/
    class Estadisticas{
        private String posicion;
        private int minCanastas;
        private int maxCanastas;
        private double avgCanastas;
        private int numAsistenciasTotales;

        public Estadisticas(String posicion, int maxCanastas, int minCanastas, double avgCanastas, int numAsistenciasTotales) {
            this.posicion = posicion;
            this.minCanastas = minCanastas;
            this.maxCanastas = maxCanastas;
            this.avgCanastas = avgCanastas;
            this.numAsistenciasTotales = numAsistenciasTotales;
        }

        public String getPosicion() {
            return posicion;
        }

        public int getMinCanastas() {
            return minCanastas;
        }

        public int getMaxCanastas() {
            return maxCanastas;
        }

        public double getAvgCanastas() {
            return avgCanastas;
        }

        public int getNumAsistenciasTotales() {
            return numAsistenciasTotales;
        }
    }
    @GetMapping("/posiciones-estadisticas")
    public Map<String, Estadisticas> getEstadisticasGroupedByPosicion(){
        List<Object[]> jugadores = jugadorRepository.AvgMaxMinCanastas();

        Map<String, Estadisticas> estadisticasMap = new HashMap<>();

        for (Object[] p: jugadores) {
            Estadisticas aux = new Estadisticas((String) p[0], (int) p[1], (int) p[2], (double) p[3], (int) p[4]);
            estadisticasMap.put(aux.getPosicion(), aux);
        }
        return estadisticasMap;
    }

    @GetMapping("/asistencias-estadisticas")
    public Map<String, Estadisticas > getEstadisticasGroupedByAsistencias(){
        List<Object[]> jugadores = jugadorRepository.AvgAsistenciasTotales();

        Map<String, Estadisticas> estadisticasMap = new HashMap<>();

        for (Object[] p: jugadores) {
            Estadisticas aux = new Estadisticas((String) p[0], (int) p[1], (int) p[2], (double) p[3], (int) p[4]);
            estadisticasMap.put(aux.getPosicion(), aux);
        }
        return estadisticasMap;
    }



    //enumeración de posibles valores de nivel en un jugador ( igual que hacia en una clase independiente ).
    public enum NivelJugador {Bajo, Medio, Alto, Estrella};
    //Prueba grouping by
    @GetMapping("/por-nivel")
    public Map<NivelJugador, List<Jugador>> getJugadoresGroupByNivel(){
        return jugadorRepository
                .findAll()
                .parallelStream()
                .collect(Collectors.groupingBy(
                        jugador -> {
                            if (jugador.getNumCanastasTotales() < 5) return NivelJugador.Bajo;
                            else if (jugador.getNumCanastasTotales() < 40) return NivelJugador.Medio;
                            else if(jugador.getNumCanastasTotales() < 90) return NivelJugador.Alto;
                            else return NivelJugador.Estrella;
                        }
                ));
    }
}