package com.krakedev.veterinaria;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/veterinaria")
public class VeterinariaController {
    
    @GetMapping("/bienvenida")
    public String darBienvenida() {
        return "Bienvenido al Sistema de Gestión Veterinaria";
    }
    
}
