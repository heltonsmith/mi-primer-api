package com.apirest.controller;

import com.apirest.model.entity.Venta;
import com.apirest.service.IVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class VentaController {

    @Autowired
    private IVenta ventaService;

    // Endpoint para crear una venta
    @PostMapping("venta")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        // Guarda la venta en la base de datos
        Venta savedVenta = ventaService.save(venta);
        // Devuelve una respuesta con la venta guardada y el código de estado CREATED
        return new ResponseEntity<>(savedVenta, HttpStatus.CREATED);
    }

    // Endpoint para buscar una venta por su id
    @GetMapping("venta/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Venta> findById(@PathVariable Integer id) {
        // Busca la venta por su id en la base de datos
        Venta venta = ventaService.findById(id);
        // Si se encuentra la venta, se devuelve una respuesta con la venta y el código de estado OK
        if (venta != null) {
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } else {
            // Si no se encuentra la venta, se devuelve una respuesta con código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una venta por su id
    @DeleteMapping("venta/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        // Busca la venta por su id en la base de datos
        Venta venta = ventaService.findById(id);
        // Si se encuentra la venta, se elimina y se devuelve una respuesta con código de estado NO_CONTENT
        if (venta != null) {
            ventaService.delete(venta);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Si no se encuentra la venta, se devuelve una respuesta con código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
