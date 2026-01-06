package com.krakedev.inventario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.inventario.entity.Message;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private List<Message> mensajes = new ArrayList<>();

    public MessageController(){
        mensajes.add(new Message(1, "Krake Dev Escuela de Programacion"));
        mensajes.add(new Message(2, "SpringBoot desde 0"));

    }

    @GetMapping
    public List<Message> listarMensajes(){
        return mensajes;
    }
}
