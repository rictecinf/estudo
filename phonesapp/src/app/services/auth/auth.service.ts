import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from '../../../environments/environment';
import { User } from '../../models/shared/user'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = environment.domain;

  constructor(private http: HttpClient) { }


  async login(user: User): Promise<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/x-www-form-urlencoded; charset=utf-8",
        Authorization:
          "Basic " + btoa(`${user.login}:${user.password}`)
      }),
    };

    console.log(httpOptions);

    return await this.http.get(this.url + "login", httpOptions).toPromise();
    
  }

  processUser(user: User) {
    sessionStorage.setItem("user", JSON.stringify(user));
  }

  isValidUser() {
    return sessionStorage.getItem("user") != null;
  }

}
