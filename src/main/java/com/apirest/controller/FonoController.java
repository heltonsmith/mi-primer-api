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

    @PostMapping("fono")
    @ResponseStatus(HttpStatus.CREATED)
    public Fono create(@RequestBody Fono fono) {
        return fonoService.save(fono);
    }

    @PostMapping("fono/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FonoResponseDTO> create2(@RequestBody Fono fono) {
        Fono savedFono = fonoService.save(fono);
        Cliente cliente = clienteService.findById(fono.getCliente().getIdCliente());
        String clienteNombre = cliente != null ? cliente.getNombre() + " " + cliente.getApellido() : null;
        FonoResponseDTO responseDTO = new FonoResponseDTO(HttpStatus.CREATED.value(), savedFono.getId(), savedFono.getNumero(), clienteNombre);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("fono")
    @ResponseStatus(HttpStatus.CREATED)
    public Fono update(@RequestBody Fono fono) {
        return fonoService.save(fono);
    }

    @DeleteMapping("fono/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Fono fono = fonoService.findById(id);
        if (fono != null) {
            fonoService.delete(fono);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Fono not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("fono/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Fono showById(@PathVariable Integer id) {
        return fonoService.findById(id);
    }

    @GetMapping("fonos")
    @ResponseStatus(HttpStatus.OK)
    public List<Fono> showAll() {
        return fonoService.findAll();
    }
}
