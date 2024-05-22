package com.apirest.webpay.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webpay.entity.CaptureRequest;
import com.apirest.webpay.entity.RefundRequest;
import com.apirest.webpay.entity.TransaccionRequest;
import com.apirest.webpay.entity.TransbankProperties;

import cl.transbank.common.IntegrationType;
import cl.transbank.webpay.common.WebpayOptions;
import cl.transbank.webpay.exception.TransactionCaptureException;
import cl.transbank.webpay.exception.TransactionCommitException;
import cl.transbank.webpay.exception.TransactionCreateException;
import cl.transbank.webpay.exception.TransactionRefundException;
import cl.transbank.webpay.exception.TransactionStatusException;
import cl.transbank.webpay.webpayplus.WebpayPlus.Transaction;
import jakarta.annotation.PostConstruct;


@RestController
public class WebPayController {

    // Objeto que define las propiedades para la integración de Transbank
    @Autowired
    private TransbankProperties transbankProperties;

    // Esta URL es la del ambiente de prueba local quen consulta esta API
    static final String LOCAL_ADDRESS = "http://localhost:8090";
    // Nombre de la key del token que se nevía como parametro en la URL
    static final String TOKEN_KEY = "token_ws";
    // Configuración de Webpay
    private WebpayOptions webpayOptions;

    @PostConstruct
    public void init() {
        webpayOptions = new WebpayOptions(
                transbankProperties.getCommerceCode(),
                transbankProperties.getApiKey(),
                IntegrationType.TEST);
    }

    @CrossOrigin(origins = LOCAL_ADDRESS)
    @PostMapping("/webpay")
    public ResponseEntity<Object> createTransaccion(
            @RequestBody TransaccionRequest request) {
        try {

            // Datos necesarios para generar la consulta
            String buyOrder = request.getBuyOrder();
            String sessionId = request.getSessionId(); // este valor debe ser unico en integración
            double amount = request.getAmount();
            String returnUrl = request.getReturnUrl();

            // Creación de la transacción
            Transaction transaction = new Transaction(webpayOptions);

            // Inicializar la transacción enviando los datos de la compra en el JSON
            return ResponseEntity.ok(transaction.create(
                    buyOrder,
                    sessionId,
                    amount,
                    returnUrl));
        } catch (TransactionCreateException e) {
            // Manejo de excepciones directamente relacionadas al reverso
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (IOException e) {
            // Manejo de excepciones de entrada/salida
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @CrossOrigin(origins = LOCAL_ADDRESS)
    @PostMapping("/webpay/commit")
    public ResponseEntity<Object> commitTransaction(
            @RequestParam(TOKEN_KEY) String token) {
        try {

            // Creación de la transacción
            Transaction transaction = new Transaction(webpayOptions);

            // Commit de la transacción enviando el token como parametro
            return ResponseEntity.ok(transaction.commit(token));
        } catch (TransactionCommitException e) {
            // Manejo de excepciones directamente relacionadas al reverso
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (IOException e) {
            // Manejo de excepciones de entrada/salida
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = LOCAL_ADDRESS)
    @PostMapping("/webpay/search")
    public ResponseEntity<Object> searchTransaction(
            @RequestParam(TOKEN_KEY) String token) {

        try {

            // Creación de la transacción
            Transaction transaction = new Transaction(webpayOptions);

            // Busqueda de la transacción enviando el token como parametro
            return ResponseEntity.ok(transaction.status(token));
        } catch (TransactionStatusException e) {
            // Manejo de excepciones directamente relacionadas al reverso
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (IOException e) {
            // Manejo de excepciones de entrada/salida
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = LOCAL_ADDRESS)
    @PostMapping("/webpay/refund")
    public ResponseEntity<Object> refundTransaction(
            @RequestParam(TOKEN_KEY) String token, @RequestBody RefundRequest request) {

        try {

            // Creación de la transacción
            Transaction transaction = new Transaction(webpayOptions);
            // Datos necesarios para generar la consulta
            String tokenWs = token;
            double amount = request.getAmount();
            // Reversa de la transacción enviando el token y monto como parametro
            return ResponseEntity.ok(transaction.refund(tokenWs, amount));

        } catch (TransactionRefundException e) {
            // Manejo de excepciones directamente relacionadas al reverso
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (IOException e) {
            // Manejo de excepciones de entrada/salida
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @CrossOrigin(origins = LOCAL_ADDRESS)
    @PostMapping("/webpay/capture")
    public ResponseEntity<Object> captureTransaction(@RequestParam(TOKEN_KEY) String token,
            @RequestBody CaptureRequest request) {

        try {

            // Creación de la transacción
            Transaction transaction = new Transaction(webpayOptions);
            // Datos necesarios para generar la consulta
            String buyOrder = request.getBuyOrder();
            String authorizationCode = request.getAuthorizationCode();
            double amount = request.getAmount();

            // Captura de la transacción enviando el objeto de la transacción en el JSON
            return ResponseEntity.ok(transaction.capture(
                    token,
                    buyOrder,
                    authorizationCode,
                    amount));
        } catch (TransactionCaptureException e) {
            // Manejo de excepciones directamente relacionadas al reverso
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (IOException e) {
            // Manejo de excepciones de entrada/salida
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
