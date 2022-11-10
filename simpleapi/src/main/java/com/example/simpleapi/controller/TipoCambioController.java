package com.example.simpleapi.controller;

import com.example.simpleapi.SoapClient;
import com.example.simpleapi.models.CambioModel;
import com.example.simpleapi.services.TipoCambioService;
import com.example.simpleapi.wsdl.ArrayOfVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/exchangerate")
public class TipoCambioController {
        private String SOAP_URL = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL";
        @Autowired
        private TipoCambioService tipoCambioService;
        @Autowired
        private SoapClient soapClient;

        //ruta para obtner el promedio
        @GetMapping("{dateInit}/{dateFinish}")
        public ResponseEntity<Map<String, Double>> getVariable(@PathVariable String dateInit, @PathVariable String dateFinish) throws Exception {
            Map<String, Double> response = soapClient.getExchangeRateAverage(SOAP_URL,dateInit,dateFinish);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        //ruta para obtener peticiones realizadas
        @GetMapping("all")
        public ArrayList<CambioModel> getAllExchange(){
            return tipoCambioService.getExchangeRate();
        }

        //ruta para obtener modendas y tipos
        @GetMapping ("/variables")
        public ArrayOfVariable getVariable() throws Exception {
            ArrayOfVariable response = soapClient.getVariables(SOAP_URL);
            return response;
        }

    }

