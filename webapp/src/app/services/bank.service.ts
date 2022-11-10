import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GET_ALL_EXCHANGE_TYPE, GET_AVERAGE_BY_RANGE, GET_VARIABLES} from "../config/endpoints";
import {lastValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private httpClient: HttpClient) { }

  public async getListExchangeType():Promise<any>{
    const response$ =  this.httpClient.get(`${GET_ALL_EXCHANGE_TYPE}`);
    return await lastValueFrom(response$);
  }

  public async getCoinList():Promise<any>{
    const response$ =  this.httpClient.get(`${GET_VARIABLES}`);
    return await lastValueFrom(response$);
  }


  public async getAverageByRange(dateInit: String,dateFinish: String){
    const response$ =  this.httpClient.get(`${GET_AVERAGE_BY_RANGE}/${dateInit}/${dateFinish}`);
    return await lastValueFrom(response$);
  }


}
