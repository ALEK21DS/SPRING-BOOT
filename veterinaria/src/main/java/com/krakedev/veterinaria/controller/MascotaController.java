package com.krakedev.veterinaria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.veterinaria.entity.Mascota;

@RestController
@RequestMapping("/api/veterinaria")
public class MascotaController {
    private List<Mascota> mascotas = new ArrayList<>();

    public MascotaController(){
        mascotas.add(new Mascota(1, "Firulais", "Perro", 3, "Juan Perez"));
        mascotas.add(new Mascota(2, "Michi", "Gato", 2, "Ana Gomez"));
        mascotas.add(new Mascota(3, "Nemo", "Pez", 1, "Carlos Lopez"));
        mascotas.add(new Mascota(4, "Bobby", "Perro", 4, "Luisa Fernandez"));
        mascotas.add(new Mascota(5, "Luna", "Gato", 5, "Miguel Torres"));
    }

    @RequestMapping("/mascotas")
    public List<Mascota> listarMascotas(){
        return mascotas;
    }
}
