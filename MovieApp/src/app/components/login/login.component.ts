import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/User';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User = new User();
  form: FormGroup;
  private formSubmitAttempt: boolean;
  invalidLogin = false
  public bearerToken: any;
  submitMessage: String;
  loginUrl = 'http://localhost:8080/api/v1/auth/login';
  constructor(private fb: FormBuilder,private router: Router,
    private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  isFieldInvalid(field: string) {
    return (
      (!this.form.get(field).valid && this.form.get(field).touched) ||
      (this.form.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit() {
    this.user.userId = this.form.value.userName;
    this.user.userPassword = this.form.value.password;
    console.log("Input data"+JSON.stringify(this.user));
    this.authService.authenticate(JSON.stringify(this.user)).subscribe(
      (response) => {
        console.log(response['token']);
        this.authService.setBearerToken(response['token']);
        this.authService.setUserId(this.user.userId);
        this.router.navigate([''])
      },
      err => this.handleErrorResponse(err)
    );
  }
  handleErrorResponse(error: HttpErrorResponse): void {
    if (error.status === 404) {
      this.invalidLogin = true
      this.submitMessage = `Http failure response for ${this.loginUrl}: 404 Not Found`;
    } else {
      this.submitMessage = error.error.message;
    }
  }

  onSkip(){
    this.router.navigate([''])
  }

}
