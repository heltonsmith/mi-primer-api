package com.apirest.divisas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.apirest.divisas.entity.Divisa;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import reactor.core.publisher.Mono;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/divisas") // Define la ruta base para todas las solicitudes manejadas por este controlador
public class DivisaController {

    private final WebClient webClient; // Cliente WebClient para realizar solicitudes HTTP

    // Constructor que inicializa el WebClient con la URL base de la API externa
    public DivisaController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://mindicador.cl/api").build();
    }

    // Método que maneja solicitudes GET para obtener todas las divisas
    @GetMapping
    public Mono<Map<String, Divisa>> getDivisas() {
        // Realiza una solicitud GET a la API externa y convierte la respuesta en un Map
        return webClient.get()
                .retrieve() // Envía la solicitud
                .bodyToMono(Map.class) // Convierte la respuesta a un Mono de tipo Map
                .map(this::convertToDivisas); // Convierte el Map de respuesta en un Map de objetos Divisa
    }

    // Método que maneja solicitudes GET para obtener una divisa específica por su código
    @GetMapping("/{codigo}")
    public Mono<ResponseEntity<Object>> getDivisa(@PathVariable String codigo) {
        // Llama al método getDivisas para obtener todas las divisas y luego busca la divisa con el código especificado
        return getDivisas()
                .map(divisas -> {
                    Divisa divisa = divisas.get(codigo); // Obtiene la divisa del Map
                    if (divisa == null) {
                        String error = "No se encontró la divisa con código: " + codigo;
                        // Si la divisa no se encuentra, devuelve un error 404 con un mensaje
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
                    }
                    // Si la divisa se encuentra, devuelve la divisa en el cuerpo de la respuesta
                    return ResponseEntity.ok(divisa);
                });
    }

    // Método que maneja solicitudes GET para convertir una cantidad de una divisa a otra
    @GetMapping("/convert")
    public Mono<ResponseEntity<Object>> convert(
            @RequestParam String from, // Parámetro de solicitud que especifica la divisa de origen
            @RequestParam String to, // Parámetro de solicitud que especifica la divisa de destino
            @RequestParam double amount) { // Parámetro de solicitud que especifica la cantidad a convertir
        // Llama al método getDivisas para obtener todas las divisas y luego realiza la conversión
        return getDivisas()
                .map(divisas -> {
                    Divisa divisaFrom = divisas.get(from); // Obtiene la divisa de origen del Map
                    Divisa divisaTo = divisas.get(to); // Obtiene la divisa de destino del Map
                    if (divisaFrom == null) {
                        // Si la divisa de origen no se encuentra, devuelve un error 404 con un mensaje
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la divisa con código: " + from);
                    }
                    if (divisaTo == null) {
                        // Si la divisa de destino no se encuentra, devuelve un error 404 con un mensaje
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la divisa con código: " + to);
                    }
                    // Calcula la tasa de conversión y el resultado de la conversión
                    double rate = divisaFrom.getValor() / divisaTo.getValor();
                    double result = amount * rate;
                    // Devuelve el resultado de la conversión en el cuerpo de la respuesta
                    return ResponseEntity.ok(result);
                });
    }

    // Método privado que convierte un Map de respuesta en un Map de objetos Divisa
    private Map<String, Divisa> convertToDivisas(Map<String, Object> response) {
        Map<String, Divisa> divisas = new HashMap<>(); // Crea un nuevo Map para almacenar las divisas

        // Recorre el Map de respuesta y convierte cada entrada en un objeto Divisa
        response.forEach((key, value) -> {
            if (value instanceof Map) {
                // Si el valor es un Map, lo convierte en un objeto Divisa
                @SuppressWarnings("unchecked")
                Map<String, Object> divisaMap = (Map<String, Object>) value;
                Divisa divisa = new Divisa();
                divisa.setCodigo((String) divisaMap.get("codigo"));
                divisa.setNombre((String) divisaMap.get("nombre"));
                divisa.setUnidadMedida((String) divisaMap.get("unidad_medida"));
                divisa.setFecha(OffsetDateTime.parse((String) divisaMap.get("fecha")));
                divisa.setValor(((Number) divisaMap.get("valor")).doubleValue());
                // Agrega la divisa al Map de divisas
                divisas.put(key, divisa);
            }
        });

        return divisas; // Devuelve el Map de divisas
    }
}
