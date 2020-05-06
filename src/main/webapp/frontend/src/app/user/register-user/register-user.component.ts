import {Component, OnInit} from '@angular/core';
import {FormBuilder, NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";
import {Role} from "../../model/role";
import {User} from "../../model/user";
import {ComponentContent} from "../../model/component.content";

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {


  user: User;
  allRoles: Role[];

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) {
  }

  ngOnInit() {
    this.user = new User();
    this.apiService.getRoles().subscribe(
      data => {
        this.allRoles = data
      }
    );
  }


  onSubmit(userInfo : NgForm) {
    let user: User = User.deserialize(userInfo.value);

    this.apiService.registerNewUser(user)
      .subscribe(data => {
        this.router.navigate(['users']);
      }, error => {

      });
  }

}
