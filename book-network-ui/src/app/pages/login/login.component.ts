import {Component} from '@angular/core';
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    FormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private tokenService: TokenService
  ) {

  }

  register() {
    this.router.navigate(['/register']);
  }

  login() {
    this.errorMsg = [];
    this.authenticationService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (response) => {
        this.tokenService.token = response.token as string;
        this.router.navigate(['books']);
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
}
