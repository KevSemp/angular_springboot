import { Component, OnInit } from '@angular/core';
import {BankService} from "../../services/bank.service";
import {CoinInterface} from "../../interfaces/CoinInterface";


@Component({
  selector: 'app-listcoins',
  templateUrl: './listcoins.component.html',
  styleUrls: ['./listcoins.component.css']
})
export class ListcoinsComponent implements OnInit {
  coinList: CoinInterface[]=[];
  pageChanged:number=1;
  constructor(private bankService: BankService) { }

  ngOnInit(): void {
    this.bankService.getCoinList().then((response:any)=>{
      if(response.variable) {
        this.coinList = response?.variable;
      }
    })
  }

}
