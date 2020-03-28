import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {User} from "../model/user";
import {Role} from "../model/role";
import {ConstantAdapter} from "../core/adapter/constant.adapter";
import {Constant} from "../core/constant";
import {map} from "rxjs/operators";

@Injectable()
export class ApiService {

  constructor(private http: HttpClient, private adapter:ConstantAdapter) { }

  login(user) : Observable<string>{
    return this.http.post('http://localhost:8090/' + 'api/generate-token', user, {responseType: 'text'});
  }

  getUsers() : Observable<User[]>{
    return this.http.get<User[]>('http://localhost:8090/' + 'api/users');
  }

  getRoles():Observable<Role[]>{
    return this.http.get('http://localhost:8090/' + 'api/roles').pipe(
      map((data: any[]) => data.map(
        item => (this.adapter.adapt(item) as Role)
      )
    ));
  }

  registerNewUser(userInfo: any): Observable<string> {
    let user:User = new User();
    user.deserialize(userInfo);
    return this.http.post('http://localhost:8090/api/register', user,{responseType: 'text'});
  }


}
