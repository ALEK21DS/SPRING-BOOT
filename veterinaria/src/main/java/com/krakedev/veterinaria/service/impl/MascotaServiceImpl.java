package com.krakedev.veterinaria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.veterinaria.entity.Mascota;
import com.krakedev.veterinaria.repository.MascotaRepository;
import com.krakedev.veterinaria.service.MascotaService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService{

    private final MascotaRepository mascotaRepository;

    @Override
    public Mascota registrarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public List<Mascota> listarMascota() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> buscarPorNombre(String nombre) {
        return mascotaRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Mascota> buscarPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    @SneakyThrows
    public Mascota actualizaMascota(Long id, Mascota mascota) {
        Mascota mascotaExistente = mascotaRepository.findById(id)
        .orElseThrow(()-> new Exception("Mascota con ID " + id + " no encontrada"));

        mascotaExistente.setNombre(mascota.getNombre());
        mascotaExistente.setEspecie(mascota.getEspecie());
        mascotaExistente.setEdad(mascota.getEdad());
        mascotaExistente.setFechaRegistro(mascota.getFechaRegistro());
        mascotaExistente.setNombreDueno(mascota.getNombreDueno());

        Mascota mascotaActualizada = mascotaRepository.save(mascotaExistente);
        return mascotaActualizada;
    }

    @Override
    @SneakyThrows
    public void eliminarMascota(Long id) {
        mascotaRepository.findById(id)
        .orElseThrow(()-> new Exception("Mascota con ID " + id + " no encontrada"));

        mascotaRepository.deleteById(id);
    }

}
