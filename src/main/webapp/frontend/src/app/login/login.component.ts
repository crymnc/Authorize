import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ApiService} from "../service/api.service";
import {LoginPage} from "../model/login.page";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginPage: LoginPage = new LoginPage();
  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: ApiService) {
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    this.loginPage.successMessage = '';
    this.loginPage.errorMessage = '';
    this.loginPage.isSuccess = false;
    this.loginPage.hasError = false;
    const loginPayload = {
      username: this.loginForm.controls.username.value,
      password: this.loginForm.controls.password.value
    }
    this.apiService.login(loginPayload).subscribe(data => {
      this.loginPage.successMessage = data.message;
      this.loginPage.isSuccess = true;
      if (data.statusCode === 200) {
        window.localStorage.setItem('token', data.result);
        //this.router.navigate(['index']);
      }
    }, data => {
      this.loginPage.hasError = true;
      this.loginPage.errorMessage = data.error.message;
    });
  }

  ngOnInit() {
    window.localStorage.removeItem('token');
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.required]
    });
  }

}
