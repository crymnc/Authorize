import {Component, OnInit} from '@angular/core';
import {FormBuilder, NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {ApiService} from "../../service/api.service";
import {Role} from "../../model/role";
import {User} from "../../model/user";
import {ErrorSuccessMessage} from "../../model/base/errorSuccessMessage";

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent extends ErrorSuccessMessage implements OnInit {


  user: User;
  allRoles: Role[];

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) {
    super();
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
    this.apiService.registerNewUser(userInfo.value)
      .subscribe(data => {
        this.router.navigate(['users']);
      }, error => {
        this.hasError =true;
        this.errorMessage = JSON.parse(error.error).message;
      });
  }

}
