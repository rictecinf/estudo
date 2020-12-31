import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Phone } from '../models/shared/phone'
import { AuthService } from '../services/auth/auth.service';
import { PhoneService } from '../services/phone/phone.service'

@Component({
  selector: 'app-phone-details',
  templateUrl: './phone-details.component.html',
  styleUrls: ['./phone-details.component.css']
})
export class PhoneDetailsComponent implements OnInit {

  phone: Phone;

  constructor(private phoneService: PhoneService, private activatedroute: ActivatedRoute, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    if (!this.authService.isValidUser()) {
      this.router.navigateByUrl('');
    } else {
      this.activatedroute.paramMap.subscribe(paramMap => {
        if(paramMap != null) {
          this.phoneDetails(paramMap.get("code"));
        } else {
          this.router.navigateByUrl('listall');
        }
      });
    }
  }

  phoneDetails(code: string) {
    this.phoneService.details(code).then(response => {
      this.phone = response;
    }).catch(error => {
      console.log(error);
    });
  }
}
