package com.apirest.controller;

import com.apirest.model.entity.Cuenta;
import com.apirest.service.ICuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CuentaController {

    @Autowired
    private ICuenta cuentaService;

    @PostMapping("cuenta")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cuenta> create(@RequestBody Cuenta cuenta) {
        Cuenta savedCuenta = cuentaService.save(cuenta);
        return new ResponseEntity<>(savedCuenta, HttpStatus.CREATED);
    }

    @PostMapping("cuenta/login")
    public ResponseEntity<String> login(@RequestBody Cuenta cuenta) {
        // Buscar la cuenta por usuario
        Cuenta cuentaEncontrada = cuentaService.findByUsuario(cuenta.getUsuario());

        // Verificar si la cuenta existe y la clave es correcta
        if (cuentaEncontrada != null && cuentaEncontrada.getClave().equals(cuenta.getClave())) {
            return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("cuenta/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cuenta> findByUsuario(@PathVariable String usuario) {
        Cuenta cuenta = cuentaService.findByUsuario(usuario);
        if (cuenta != null) {
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("cuenta/{usuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String usuario) {
        Cuenta cuenta = cuentaService.findByUsuario(usuario);
        if (cuenta != null) {
            cuentaService.delete(cuenta);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
