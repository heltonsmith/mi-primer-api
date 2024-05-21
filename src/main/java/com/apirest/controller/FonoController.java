package com.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apirest.model.dto.FonoResponseDTO;
import com.apirest.model.entity.Cliente;
import com.apirest.model.entity.Fono;
import com.apirest.service.ICliente;
import com.apirest.service.IFono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class FonoController {

    @Autowired
    private IFono fonoService;

    @Autowired
    private ICliente clienteService;

    // Endpoint para crear un fono
    @PostMapping("fono")
    @ResponseStatus(HttpStatus.CREATED)
    public Fono create(@RequestBody Fono fono) {
        return fonoService.save(fono);
    }

    // Endpoint para crear un fono con respuesta detallada
    @PostMapping("fono/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FonoResponseDTO> create2(@RequestBody Fono fono) {
        // Guarda el fono en la base de datos
        Fono savedFono = fonoService.save(fono);
        // Busca el cliente asociado al fono
        Cliente cliente = clienteService.findById(fono.getCliente().getIdCliente());
        // Obtiene el nombre completo del cliente
        String clienteNombre = cliente != null ? cliente.getNombre() + " " + cliente.getApellido() : null;
        // Crea un objeto FonoResponseDTO con los datos del fono y el nombre del cliente
        FonoResponseDTO responseDTO = new FonoResponseDTO(HttpStatus.CREATED.value(), savedFono.getId(), savedFono.getNumero(), clienteNombre);
        // Devuelve una respuesta con el objeto FonoResponseDTO y el código de estado CREATED
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Endpoint para actualizar un fono
    @PutMapping("fono")
    @ResponseStatus(HttpStatus.CREATED)
    public Fono update(@RequestBody Fono fono) {
        return fonoService.save(fono);
    }

    // Endpoint para eliminar un fono por su ID
    @DeleteMapping("fono/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        // Busca el fono por su ID en la base de datos
        Fono fono = fonoService.findById(id);
        // Si se encuentra el fono, se elimina y se devuelve una respuesta con código de estado NO_CONTENT
        if (fono != null) {
            fonoService.delete(fono);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Si no se encuentra el fono, se devuelve una respuesta con mensaje de error y código de estado NOT_FOUND
            return new ResponseEntity<>("Fono not found", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener un fono por su ID
    @GetMapping("fono/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Fono showById(@PathVariable Integer id) {
        return fonoService.findById(id);
    }

    // Endpoint para obtener todos los fonos
    @GetMapping("fonos")
    @ResponseStatus(HttpStatus.OK)
    public List<Fono> showAll() {
        return fonoService.findAll();
    }
}
