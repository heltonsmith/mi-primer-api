package com.apirest.controller;

import com.apirest.model.entity.Vendedor;
import com.apirest.service.IVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class VendedorController {

    @Autowired
    private IVendedor vendedorService;

    // Endpoint para crear un vendedor
    @PostMapping("vendedor")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vendedor> create(@RequestBody Vendedor vendedor) {
        // Guarda el vendedor en la base de datos
        Vendedor savedVendedor = vendedorService.save(vendedor);
        // Devuelve una respuesta con el vendedor guardado y el código de estado CREATED
        return new ResponseEntity<>(savedVendedor, HttpStatus.CREATED);
    }

    // Endpoint para buscar un vendedor por su rut
    @GetMapping("vendedor/{rut}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Vendedor> findByRut(@PathVariable String rut) {
        // Busca el vendedor por su rut en la base de datos
        Vendedor vendedor = vendedorService.findByRut(rut);
        // Si se encuentra el vendedor, se devuelve una respuesta con el vendedor y el código de estado OK
        if (vendedor != null) {
            return new ResponseEntity<>(vendedor, HttpStatus.OK);
        } else {
            // Si no se encuentra el vendedor, se devuelve una respuesta con código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un vendedor por su rut
    @DeleteMapping("vendedor/{rut}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String rut) {
        // Busca el vendedor por su rut en la base de datos
        Vendedor vendedor = vendedorService.findByRut(rut);
        // Si se encuentra el vendedor, se elimina y se devuelve una respuesta con código de estado NO_CONTENT
        if (vendedor != null) {
            vendedorService.delete(vendedor);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Si no se encuentra el vendedor, se devuelve una respuesta con código de estado NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
