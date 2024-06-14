package com.apirest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.apirest.model.entity.Cliente;
import com.apirest.service.ICliente;
import com.apirest.service.IDetalleVenta;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICliente clienteService;

    @MockBean
    private IDetalleVenta detalleService;

    @Test
    void testShowAll() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setIdCliente(1);
        cliente1.setNombre("Cliente 1");

        Cliente cliente2 = new Cliente();
        cliente2.setIdCliente(2);
        cliente2.setNombre("Cliente 2");

        ArrayList<Cliente> clientes = new ArrayList<>(Arrays.asList(cliente1, cliente2));

        when(clienteService.findByAll()).thenReturn(clientes);

        mockMvc.perform(get("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCliente").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Cliente 1"))
                .andExpect(jsonPath("$[1].idCliente").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Cliente 2"));
    }
}
