import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { environment } from '../../../environments/environment';
import { Phone } from '../../models/shared/phone'
import { User } from 'src/app/models/shared/user';

@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  user: User = new User();
  url = environment.domain;
  httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" })
  };
  

  constructor(private http: HttpClient) {
    this.loadUser();
    this.httpOptions = {
      headers: new HttpHeaders({ 
        'Content-Type': 'application/json',
        Authorization:
          "Basic " + btoa(`${this.user.login}:${this.user.password}`)
      })
    };
   }

  async register(phone: Phone): Promise<any> {
    return await this.http.post<Phone>(this.url + "claro/mobile", JSON.stringify(phone), this.httpOptions)
      .toPromise();
  }

  async details(code: string) {
    return await this.http.get<Phone>(this.url + 'claro/mobile/' + code, this.httpOptions)
      .toPromise();
  }

  async listAll() {
    return await this.http.get<Phone[]>(this.url + 'claro/mobile', this.httpOptions)
      .toPromise();
  }

  private loadUser() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
  }

}
