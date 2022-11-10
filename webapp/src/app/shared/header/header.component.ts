import { Component, OnInit } from '@angular/core';
import {UsersService} from "../../services/users.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private userService:UsersService) { }

  ngOnInit(): void {
    this.userService.getUser().then((response)=>{
      console.log(response);
    })
  }

}