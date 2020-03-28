import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ApiService} from "../service/api.service";
import {User} from "../model/user";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  users: User[];
  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.apiService.getUsers()
      .subscribe(
        data => {this.users = data;},
        error => {
          error;
        });
  }

}
