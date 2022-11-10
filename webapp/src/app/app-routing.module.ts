import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ExchangeTableComponent} from "./public/exchange-table/exchange-table.component";
import {ListcoinsComponent} from "./public/listcoins/listcoins.component";

const routes: Routes = [
  { path: '', component: ExchangeTableComponent
  },
  {
    path: 'monedas',component: ListcoinsComponent
  },
  {
    path: 'promedio',component: ExchangeTableComponent
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
