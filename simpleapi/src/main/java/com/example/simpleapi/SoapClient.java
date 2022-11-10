package com.example.simpleapi;

import com.example.simpleapi.services.TipoCambioService;
import com.example.simpleapi.wsdl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.Map;

public class SoapClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(SoapClient.class);
    @Autowired
    TipoCambioService tipoCambioService;


    public ArrayOfVariable getVariables(String url) throws Exception {
        VariablesDisponibles Request = new VariablesDisponibles();
        VariablesDisponiblesResponse res = (VariablesDisponiblesResponse) getWebServiceTemplate().marshalSendAndReceive(url, Request, new SoapActionCallback("http://www.banguat.gob.gt/variables/ws/VariablesDisponibles"));
        return tipoCambioService.setMoneda(res.getVariablesDisponiblesResult().getVariables().getVariable());
    }

    public Map<String, Double> getExchangeRateAverage(String url, String dateInit, String dateFinish) throws Exception {
        TipoCambioRango Request = new TipoCambioRango();
        Request.setFechainit(dateInit);
        Request.setFechafin(dateFinish);
        TipoCambioRangoResponse requestResponse = (TipoCambioRangoResponse) getWebServiceTemplate().marshalSendAndReceive(url, Request, new SoapActionCallback("http://www.banguat.gob.gt/variables/ws/TipoCambioRango"));

         ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
         String request = ow.writeValueAsString(Request);
         String response = ow.writeValueAsString(requestResponse);
         //setting request and response binnacle
         int idRequest = tipoCambioService.setBitacora(request,response);
         //set response data into the table
         tipoCambioService.setExchangeRate(requestResponse.getTipoCambioRangoResult().getVars().getVar(),idRequest);

         return  tipoCambioService.getExchangeRateAverage(idRequest);
    }







}
