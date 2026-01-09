package com.krakedev.veterinaria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.veterinaria.entity.Mascota;


@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {
    private List<Mascota> mascotas = new ArrayList<>();

    @RequestMapping
    public List<Mascota> listarMascotas(){
        return mascotas;
    }

    @RequestMapping("/{id}")
    public Mascota obtenerMascotaPorId(@PathVariable int id){
        Optional<Mascota> mascota = mascotas.stream()
        .filter(m -> m.getId() == id).
        findFirst();

        return mascota.orElse(null);

    }

    @PostMapping
    public Mascota crearMascota(@RequestBody Mascota mascota){
        mascotas.add(mascota);

        return mascota;
    }

    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable int id){
        mascotas.removeIf(m -> m.getId() == id);
    }
}
