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

@RestController
@RequestMapping("/api/divisas")
public class DivisaController {

    private final WebClient webClient;

    public DivisaController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://mindicador.cl/api").build();
    }

    @GetMapping
    public Mono<Map<String, Divisa>> getDivisas() {
        return webClient.get()
                .retrieve()
                .bodyToMono(Map.class)
                .map(this::convertToDivisas);
    }

    @GetMapping("/{codigo}")
    public Mono<ResponseEntity<Object>> getDivisa(@PathVariable String codigo) {
        return getDivisas()
                .map(divisas -> {
                    Divisa divisa = divisas.get(codigo);
                    if (divisa == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la divisa con código: " + codigo);
                    }
                    return ResponseEntity.ok(divisa);
                });
    }

    @GetMapping("/convert")
    public Mono<ResponseEntity<Object>> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        return getDivisas()
                .map(divisas -> {
                    Divisa divisaFrom = divisas.get(from);
                    Divisa divisaTo = divisas.get(to);
                    if (divisaFrom == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la divisa con código: " + from);
                    }
                    if (divisaTo == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la divisa con código: " + to);
                    }
                    double rate = divisaFrom.getValor() / divisaTo.getValor();
                    double result = amount * rate;
                    return ResponseEntity.ok(result);
                });
    }

    private Map<String, Divisa> convertToDivisas(Map<String, Object> response) {
        Map<String, Divisa> divisas = new HashMap<>();

        response.forEach((key, value) -> {
            if (value instanceof Map) {
                Map<String, Object> divisaMap = (Map<String, Object>) value;
                Divisa divisa = new Divisa();
                divisa.setCodigo((String) divisaMap.get("codigo"));
                divisa.setNombre((String) divisaMap.get("nombre"));
                divisa.setUnidadMedida((String) divisaMap.get("unidad_medida"));
                divisa.setFecha(OffsetDateTime.parse((String) divisaMap.get("fecha")));
                divisa.setValor(((Number) divisaMap.get("valor")).doubleValue());
                divisas.put(key, divisa);
            }
        });

        return divisas;
    }
}