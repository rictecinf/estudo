import { Component, OnInit } from '@angular/core';
import { PhoneService } from '../services/phone/phone.service'
import { Phone } from '../models/shared/phone'
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-list-phones',
  templateUrl: './list-phones.component.html',
  styleUrls: ['./list-phones.component.css']
})
export class ListPhonesComponent implements OnInit {

  phoneList: Phone[];

  constructor(private phoneService: PhoneService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {

    if (!this.authService.isValidUser()) {
      this.router.navigateByUrl('');
    } else {
      this.phoneService.listAll().then(response => {
        this.phoneList = response
      }).catch(err => {
        console.log(err);
      })
    }
  }

}
