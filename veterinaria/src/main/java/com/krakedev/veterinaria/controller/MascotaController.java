package com.krakedev.veterinaria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.veterinaria.entity.Mascota;
import com.krakedev.veterinaria.service.MascotaService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
public class MascotaController {
    private final MascotaService mascotaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMascota(@RequestBody Mascota mascota){
        Mascota nuevaMascota = mascotaService.registrarMascota(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
    }

    @GetMapping()
    public ResponseEntity<List<Mascota>> listarMascotas(){
        List<Mascota> mascotas = mascotaService.listarMascota();
        return ResponseEntity.ok(mascotas);
    }
    
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        Optional<Mascota> mascota = mascotaService.buscarPorNombre(nombre);
        return mascota.isPresent() ? ResponseEntity.ok(mascota.get()) 
        :   ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
    }
    
    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.buscarPorId(id);
        return mascota.isPresent() ? ResponseEntity.ok(mascota.get()) 
        :   ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mascota no encontrada");
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        try{
            Mascota mascotaActualizada = new Mascota();
            mascotaActualizada.setNombre(mascota.getNombre());
            mascotaActualizada.setEdad(mascota.getEdad());
            mascotaActualizada.setEspecie(mascota.getEspecie());
            mascotaActualizada.setNombreDueno(mascota.getNombreDueno());
            mascotaActualizada.setFechaRegistro(mascota.getFechaRegistro());

            Mascota mascotaBDD = mascotaService.actualizaMascota(id, mascotaActualizada);
            return ResponseEntity.ok(mascotaBDD);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMascota(@PathVariable Long id){
        try{
            mascotaService.eliminarMascota(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
    
}
