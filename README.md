# angular_springboot

Proyecto que consume web service del banco de guatemala, con el objetivo de obtener el promedio de taza de compra y venta en un rango de fecha dado por el usuario.


## Frontend (Angular)

### BanckService
* Servicio que permite realizar las peticiones hacia el backend

#### Métodos

* Método que permite obtener todas las peticiones realizadas hacia el endpoint de promedio.

```js
public async getListExchangeType():Promise<any>{
    const response$ =  this.httpClient.get(`${GET_ALL_EXCHANGE_TYPE}`);
    return await lastValueFrom(response$);
  }
```

* Método que permite obtener todos los tipos de moneda

```js
 const response$ =  this.httpClient.get(`${GET_VARIABLES}`);
return await lastValueFrom(response$);
```


* Método que permite obtener el promedio de taza de compra y ventas en un rango de fechas estipulado.

```js
  const response$ =  this.httpClient.get(`${GET_AVERAGE_BY_RANGE}/${dateInit}/${dateFinish}`);
return await lastValueFrom(response$);
```



## Backend (Java)
### Rutas
   
* Ruta tipo GET que permite obtener todas las fechas consultadas al momento de realizar el promedio de compras y ventas de la taza de tipo de cambio, estas agrupadas por petición.

```java
  @GetMapping("all")
        public ArrayList<CambioModel> getAllExchange(){
            return tipoCambioService.getExchangeRate();
        }
```

* Ruta tipo GET que permite obtener el promedio de taza de compra y venta de un tipo de cambio dado un rango de fecha

```java
   @GetMapping("{dateInit}/{dateFinish}")
        public ResponseEntity<Map<String, Double>> getVariable(@PathVariable String dateInit, @PathVariable String dateFinish) throws Exception {
            Map<String, Double> response = soapClient.getExchangeRateAverage(SOAP_URL,dateInit,dateFinish);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
```

* Ruta tipo GET que retorna los tipos de moneda dados por el servicio variables 
```java
        @GetMapping ("/variables")
        public ArrayOfVariable getVariable() throws Exception {
            ArrayOfVariable response = soapClient.getVariables(SOAP_URL);
            return response;
        }
```

## Base de Datos (MySql)

### Scripts

#### Creacion de tablas

* Tabla que almacena el tipo de moneda

```
    create table cat_moneda(
    moneda int not null primary key,
    descripcion varchar(75)
    );
```

* Tabla que almacena el request y response realizado al momento de realizar una consulta por promedio

```
   create table bitacora(
    id int not null primary key auto_increment,
    request json,
    response json
);
```

* Tabla que almacena el tipo de cambio , esta relacioanada a una petición.

```
  create table tipo_cambio(
   id int not null primary key auto_increment,
   peticion_id int,
   fecha Varchar(50),
   compra double,
   venta double,
   foreign key (peticion_id) references bitacora(id)
);

```
