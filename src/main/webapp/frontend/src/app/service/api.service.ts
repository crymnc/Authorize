import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {User} from "../model/user";
import {Role} from "../model/role";
import {GlobalVariables} from "../core/global.variables";

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) {
  }

  login(user): Observable<string> {
    return this.http.post(GlobalVariables.BASE_URL + '/generate-token', user, {responseType: 'text'});
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(GlobalVariables.BASE_URL + '/user/users');
  }

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(GlobalVariables.BASE_URL + '/user/roles');
  }

  registerNewUser(userInfo: any): Observable<string> {
    return this.http.post(GlobalVariables.BASE_URL + '/user/register', userInfo, {responseType: 'text'});
  }

  deleteUser(userId: number) {
    this.http.delete(GlobalVariables.BASE_URL + '/user/' + userId);
  }


}
