import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../service/authentication.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {


  constructor(private authenticationService:AuthenticationService) {
    this.currentUser = this.authenticationService.currentUserValue;
  }
  currentUser:any;
  ngOnInit() {

  }
  private _opened: boolean = false;

  private _toggleSidebar() {
    this._opened = !this._opened;
  }

}
