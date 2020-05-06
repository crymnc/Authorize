import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterUserComponent} from "./user/register-user/register-user.component";
import {ListUserComponent} from "./user/list-user/list-user.component";
import {AuthGuard} from "./helper/auth.guard";
import {HomeComponent} from "./home/home.component";


const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard] },
  {path: 'login', component: LoginComponent},
  {path: 'user/register', component: RegisterUserComponent, canActivate: [AuthGuard] },
  {path: 'index', component: HomeComponent, canActivate: [AuthGuard] },
  {path: 'users', component: ListUserComponent, canActivate: [AuthGuard] },
  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
