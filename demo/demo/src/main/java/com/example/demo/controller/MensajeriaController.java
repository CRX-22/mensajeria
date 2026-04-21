package com.example.demo.controller;

import com.example.demo.DTO.PedidoRequestDTO;
import com.example.demo.DTO.PedidoResponseDTO;
import com.example.demo.service.MensajeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensajeria")
public class MensajeriaController {

    @Autowired
    private MensajeriaService mensajeriaService;

    /**
     * Metodo que actua como endpoint para el envio del pedido
     * @param request
     * @return
     */
    @PostMapping("")
    public ResponseEntity<PedidoResponseDTO> enviarPedido(@RequestBody PedidoRequestDTO request) {
        return ResponseEntity.ok(mensajeriaService.procesarPedido(request));
    }
}
