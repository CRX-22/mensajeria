package com.example.demo.DTO;

import lombok.Data;

@Data
public class PedidoRequestDTO {
        private String numPedido;
        private String cantidadPedido;
        private String codigoEAN;
        private String nombreProducto;
        private String numDocumento;
        private String direccion;

}