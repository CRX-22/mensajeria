package com.example.demo.service;

import com.example.demo.DTO.PedidoRequestDTO;
import com.example.demo.DTO.PedidoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
@Scope("singleton")
public class MensajeriaService {
    @Autowired
    private  RestTemplate restTemplate;

    private static final String URL = "https://run.mocky.io/v3/19217075-6d4e-4818-98bc-416d1feb7b84";

    /**
     * Metodo que procesa el pedido transformandolo de un DTO Json a XML para enviarlo a la url asignada y luego transformando la respuesta de xml a un DTO nuevamente
     * @param request
     * @return
     */
    public PedidoResponseDTO procesarPedido(PedidoRequestDTO request) {

        String xmlRequest = construirXml(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);

        HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                entity,
                String.class
        );

        return transformarRespuesta(response.getBody());
    }

    /**
     * Metodo que transforma la respuesta recibida por la url de xml a un DTO y retornarlo como json
     * @param xml
     * @return
     */

    public PedidoResponseDTO transformarRespuesta(String xml) {

        String codigo = xml.split("<Codigo>")[1].split("</Codigo>")[0];
        String mensaje = xml.split("<Mensaje>")[1].split("</Mensaje>")[0];

        return new PedidoResponseDTO(codigo, mensaje);
    }

    /**
     * Metodo que crea el xml a partir de un DTO que llega como json
     * @param request
     * @return
     */
    public String construirXml(PedidoRequestDTO request) {

        return """
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                          xmlns:env="http://WSDLs/EnvioPedidos/EnvioPedidosAcme">
           <soapenv:Header/>
           <soapenv:Body>
              <env:EnvioPedidoAcme>
                 <EnvioPedidoRequest>
                    <pedido>%s</pedido>
                    <Cantidad>%s</Cantidad>
                    <EAN>%s</EAN>
                    <Producto>%s</Producto>
                    <Cedula>%s</Cedula>
                    <Direccion>%s</Direccion>
                 </EnvioPedidoRequest>
              </env:EnvioPedidoAcme>
           </soapenv:Body>
        </soapenv:Envelope>
        """.formatted(
                request.getNumPedido(),
                request.getCantidadPedido(),
                request.getCodigoEAN(),
                request.getNombreProducto(),
                request.getNumDocumento(),
                request.getDireccion()
        );
    }
}
