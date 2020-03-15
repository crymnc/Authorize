import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {ApiResponse} from "../model/api.response";

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) { }

  login(user) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8090/' + 'api/generate-token', user);
  }


}
