import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";
import {Role} from "../../model/role";
import {User} from "../../model/user";

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {


  user:User;
  roles:Role[];
  constructor(private formBuilder: FormBuilder,private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    this.user = new User();

    this.apiService.getRoles().subscribe(
      data => {this.roles = data},
            error =>{ error;}
      );

  }

  onSubmit(userInfo : NgForm){
    this.apiService.registerNewUser(userInfo.value)
      .subscribe( data => {
        this.router.navigate(['users']);
      });
  }

}
