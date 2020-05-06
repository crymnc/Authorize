import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: User[];

  constructor(private router: Router, private apiService: ApiService) {
  }

  ngOnInit() {
    if (!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    }
    this.apiService.getUsers()
      .subscribe(
        data => {
          this.users = data;
        },
        error => {
          error;
        });
  }

  registerUser(): void {
    this.router.navigate(['register']);
  };

  deleteUser(user: User): void {
    this.apiService.deleteUser(user.id).subscribe(
      data => {
          data;
      },
      error => {
        error;
      });
    this.users = this.users.filter(u => u !== user);
  }

}
