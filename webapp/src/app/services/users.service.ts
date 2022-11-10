import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { lastValueFrom } from 'rxjs';
import {USERS} from "../config/endpoints";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private httpClient:HttpClient) { }

  public async getUser():Promise<any>{
   const response$ =  this.httpClient.get(`${USERS}`);
   return await lastValueFrom(response$);
  }
}
