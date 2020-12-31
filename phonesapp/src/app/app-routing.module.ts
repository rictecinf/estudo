import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListPhonesComponent } from './list-phones/list-phones.component';
import { LoginComponent } from './login/login.component';
import { PhoneDetailsComponent } from './phone-details/phone-details.component';
import { RegisterPhonesComponent } from './register-phones/register-phones.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'listall', component: ListPhonesComponent },
  { path: 'register', component: RegisterPhonesComponent },
  { path: 'details/:code', component: PhoneDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
