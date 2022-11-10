import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BankService} from "../../services/bank.service";
import {ExchangeInterface} from "../../interfaces/ExchangeInterface";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2'
@Component({
  selector: 'app-exchange-table',
  templateUrl: './exchange-table.component.html',
  styleUrls: ['./exchange-table.component.css']
})
export class ExchangeTableComponent implements OnInit {

  pageChanged:number=1;
  exchangeList: ExchangeInterface[]=[];
  form:FormGroup;
  averagePurchase = 0;
  averageSale = 0;
  constructor(private fb:FormBuilder,private bankService: BankService) {
    this.form = this.fb.group({
      dateInit:  ["",[Validators.required,Validators.pattern("^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$")]],
      dateFinish:  ["",[Validators.required,Validators.pattern("^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$")]],
    });

  }

  get NoValidDateInit(){
    return this.form.get('dateInit')?.invalid && this.form.get('dateInit')?.touched
  }

  get NoValidDateFinish(){
    return this.form.get('dateFinish')?.invalid && this.form.get('dateFinish')?.touched
  }


  ngOnInit(): void {
    this.bankService.getListExchangeType().then((response)=>{
      this.exchangeList = response;
    })
  }

  public send() {
    try {
      if (!this.form.invalid) {
        this.bankService.getAverageByRange(this.form.value.dateInit, this.form.value.dateFinish).then((response: any) => {
          this.averagePurchase = response.promedioVenta;
          this.averageSale = response.promedioCompra;
          if(this.averageSale !== 0 && this.averagePurchase !== 0){
            this.bankService.getListExchangeType().then((response)=>{
              this.exchangeList = response;
            })
          }
        })
        return
      }
    }catch (e) {
        console.log(e);
    }
    Swal.fire({
      title: 'Error!',
      text: 'Parece que ha ocurrido un error',
      icon: 'error',
      confirmButtonText: 'Continuar'
    })




  }
}
