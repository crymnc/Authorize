import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {MainComponent} from "./main/main.component";
import {RegisterUserComponent} from "./user/register-user/register-user.component";
import {ListUserComponent} from "./user/list-user/list-user.component";


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: LoginComponent},
  {path: 'index', component: MainComponent},
  {path: 'register', component: RegisterUserComponent},
  {path: 'users', component: ListUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
