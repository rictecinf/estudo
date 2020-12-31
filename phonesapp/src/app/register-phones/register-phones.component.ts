import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Phone } from '../models/shared/phone'
import { PhoneService } from '../services/phone/phone.service'
import { ToastrService } from 'ngx-toastr';
import { IDatePickerDirectiveConfig } from 'ng2-date-picker';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-phones',
  templateUrl: './register-phones.component.html',
  styleUrls: ['./register-phones.component.css']
})
export class RegisterPhonesComponent implements OnInit {

  formPhone: FormGroup;
  phoneData: Phone;

  constructor(private formBuilder: FormBuilder, private phoneService: PhoneService, private toastr: ToastrService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.createForm(new Phone());
    if (!this.authService.isValidUser()) {
      this.router.navigateByUrl('');
    }
  }

  config: IDatePickerDirectiveConfig = {
    hours24Format: 'HH',
    showTwentyFourHours: false,
    firstDayOfWeek: 'su',
    locale: 'pt-br',
    closeOnSelect: true,
    showSeconds: false,
    format: 'DD-MM-yyyy'
  };

  createForm(phone: Phone) {
    this.formPhone = this.formBuilder.group({
      model: [phone.model],
      price: [phone.price],
      brand: [phone.brand],
      date: [phone.date],
      photo: [phone.photo]
    })
  }

  onSubmit() {
    if (this.formPhone.valid) {
      this.phoneData = new Phone();
      this.phoneData.model = this.formPhone.value.model;
      this.phoneData.price = this.formPhone.value.price;
      this.phoneData.brand = this.formPhone.value.brand;
      this.phoneData.date = this.formPhone.value.date;
      this.phoneData.photo = this.formPhone.value.photo;

      this.phoneService.register(this.phoneData).then(response => {
        this.toastr.success('OK', 'Smarthphone cadastrado!');
        this.formPhone.reset(new Phone());
      }).catch(err => {
        this.toastr.error('Erro ao cadastrar smartphone', 'Erro')
      });
    } else {
      this.toastr.warning('Formulário inválido', 'Informe todos os campos')
    }

  }
}
