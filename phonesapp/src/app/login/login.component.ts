import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service'
import { User } from '../models/shared/user'
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup;
  userData: User;

  constructor(private formBuilder: FormBuilder, private toastr: ToastrService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.createForm(new User());
    this.redirectToSmartphonesList();
  }

  onSubmit() {
    if (this.formLogin.valid) {
      this.userData = new User();
      this.userData.login = this.formLogin.value.login;
      this.userData.password = this.formLogin.value.password;
      
      this.authService.login(this.userData).then(response => {
        if(response) {
          this.authService.processUser(this.userData);
          this.redirectToSmartphonesList();
          this.formLogin.reset(new User());
        }
      }).catch(err => {
        this.toastr.error('Login inválido', 'Erro')
      });
    }
  }

  createForm(user: User) {
    this.formLogin = this.formBuilder.group({
      login: [user.login],
      password: [user.password]
    })
  }

  redirectToSmartphonesList() {
    if (this.authService.isValidUser()) {
      this.router.navigateByUrl('listall');
    }
  }

}
