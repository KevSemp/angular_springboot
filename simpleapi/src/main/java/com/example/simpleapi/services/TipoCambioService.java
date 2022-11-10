package com.example.simpleapi.services;

import com.example.simpleapi.connection.MysqlConnection;
import com.example.simpleapi.models.CambioModel;
import com.example.simpleapi.repositories.TipoCambioRepositories;
import com.example.simpleapi.wsdl.ArrayOfVariable;
import com.example.simpleapi.wsdl.Var;
import com.example.simpleapi.wsdl.Variable;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TipoCambioService {
    @Autowired
    TipoCambioRepositories tipoCambioRepositories;

    //método para ingresar moneda y descripción en la base de datos
    public ArrayOfVariable setMoneda(List<Variable> variables) throws Exception {

        ArrayOfVariable newVariables = new ArrayOfVariable();
        MysqlConnection newConnection = new MysqlConnection();
        Connection conn = newConnection.getConnection();
        for(Variable item : variables){
            if(item.getMoneda() % 2 == 0){
                newVariables.getVariable().add(item);
                try {
                    PreparedStatement posted = conn.prepareStatement("INSERT INTO cat_moneda(moneda,descripcion) VALUES(" + item.getMoneda() + ",'" + item.getDescripcion() + "'" + ")");
                    posted.executeUpdate();
                }catch (Exception e){

                }

            }
        }
        return newVariables;
    }

    //método para ingresar bitácora de requests
    public int setBitacora(String request,String response) throws Exception {
        MysqlConnection newConnection = new MysqlConnection();
        JSONObject requestJson = new JSONObject(request);
        JSONObject responseJson = new JSONObject(response);
        PreparedStatement posted = newConnection.getConnection().prepareStatement("INSERT INTO bitacora(request,response) VALUES('" + requestJson + "','" + responseJson + "'" + ")", Statement.RETURN_GENERATED_KEYS);

        if(posted.executeUpdate() == 1){
            ResultSet rs = posted.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            return generatedKey;
        };

        return -1;
    }

    //método para obtener la lista de tipos de cambios por rango
    public ArrayList<CambioModel> getExchangeRate() {
        return (ArrayList<CambioModel>) tipoCambioRepositories.findAll();
    }

    //método para ingresar los tipos de cambios
    public ArrayOfVariable setExchangeRate(List<Var> variables,int idRequest) throws Exception {

        ArrayOfVariable newVariables = new ArrayOfVariable();
        MysqlConnection newConnection = new MysqlConnection();
        Connection conn = newConnection.getConnection();
        for(Var item : variables){
                try {
                    PreparedStatement posted = conn.prepareStatement("INSERT INTO tipo_cambio(peticion_id,fecha,compra,venta) VALUES(" + idRequest + ",'" + item.getFecha()  + "','"+item.getCompra()+"','"+item.getVenta()+"'" + ")");
                    posted.executeUpdate();
                }catch (Exception e){
                    System.out.println(e);
                }
        }
        return newVariables;
    }


    //método para obtener el prómedio de taza de compras y ventas
    public Map<String, Double> getExchangeRateAverage(int requestId) throws Exception {
        MysqlConnection newConnection = new MysqlConnection();
        Connection conn = newConnection.getConnection();

        String sqlStatement  = "select peticion_id,AVG(compra) as promedio_compra,AVG(venta) as promedio_venta\n" +
                "from tipo_cambio  where peticion_id ="+requestId;

        Map<String, Double> objectResponse = new HashMap<>();
        PreparedStatement posted = conn.prepareStatement(sqlStatement);
        ResultSet result = posted.executeQuery();

        while (result.next()) {
            objectResponse.put("promedioCompra", result.getDouble("promedio_compra"));
            objectResponse.put("promedioVenta", result.getDouble("promedio_venta"));
        }

        return objectResponse;


        }
    }




