package com.example.controller;
import com.example.domain.Equipo;
import com.example.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
    @RestController
    @RequestMapping("/equipos")
    public class EquipoController {
        @Autowired
        private EquipoRepository equipoRepository;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Equipo crearEquipo(@RequestBody Equipo equipo) {
            return equipoRepository.save(equipo);
        }

        @PutMapping
        public Equipo modificarEquipo(@RequestBody Equipo equipo) {
            return equipoRepository.save(equipo);
        }

        @GetMapping
        public List<Equipo> getEquipos() {
            return equipoRepository.findAll();
        }

        @GetMapping("/{id}")
        public Equipo getEquipoPorId(@PathVariable Long id) {
            Equipo equipo = equipoRepository.findOne(id);
            return equipo;
        }

        @DeleteMapping("/{id}")
        public void eliminarEquipo(@PathVariable Long id) {
            equipoRepository.delete(id);
        }

        @GetMapping("/por-localidad")
        public Map<String, List<Equipo>> getEquiposGroupByLocalidad() {
            return equipoRepository
                    .findAll()
                    .parallelStream()
                    .collect(Collectors.groupingBy(Equipo::getLocalidad));
        }
    }

