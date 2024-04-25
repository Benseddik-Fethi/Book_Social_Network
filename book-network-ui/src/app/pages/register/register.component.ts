import {Component} from '@angular/core';
import {RegisterRequest} from "../../services/models/register-request";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registerRequest: RegisterRequest = {email: '', firstName: '', lastName: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
  ) {
  }

  register() {
   this.errorMsg = [];
   this.authenticationService.register({
     body: this.registerRequest
   }).subscribe({
     next: (response) => {
       this.router.navigate(['activate-account']);
     },
     error: (error) => {
       console.log(error)
       if (error.error.validationErrors) {
         this.errorMsg = error.error.validationErrors;
       } else {
         this.errorMsg.push(error.error.error);
       }
     }
   })
  }

  login() {
    this.router.navigate(['login']);
  }
}
