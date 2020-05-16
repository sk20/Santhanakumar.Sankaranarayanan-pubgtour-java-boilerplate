import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { Router } from '@angular/router';
import { User } from 'src/app/User';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup; 
  loading = false;
  submitted = false;
  submitMessage: String;
  loginUrl = 'http://localhost:8080/api/v1/auth/register';
  reg: User = new User();
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private _authService: AuthenticationService,
    // public routerService: RouterService
  ) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      userId: ['', Validators.required],
      userRole: ['', Validators.required],
      userPassword: ['', [Validators.required, Validators.minLength(6)]],
      userAddedDate: ['']
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;
    this.reg.firstName = this.registerForm.value.firstName;
    this.reg.lastName = this.registerForm.value.lastName;
    this.reg.userId = this.registerForm.value.userId;
    this.reg.userLikes = this.registerForm.value.userLikes;
    this.reg.userRole = this.registerForm.value.userRole;
    this.reg.userPassword = this.registerForm.value.userPassword;
    this.reg.userAddedDate = this.registerForm.value.userAddedDate;
    console.log("Input data" + JSON.stringify(this.reg));
    this._authService.authenticateUserRegister(JSON.stringify(this.reg)).subscribe(
      (response) => {
        console.log(response);
        localStorage.setItem('user', JSON.stringify(this.reg));
      },
      err => this.handleErrorResponse(err)
    );
    this.router.navigate(['login']);
    this.loading = true;
  }
  handleErrorResponse(error: HttpErrorResponse): void {
    console.log('error log ' + error.message);
    if (error.status === 404) {
      this.submitMessage = `Http failure response for ${this.loginUrl}: 404 Not Found`;
    } else {
      //  this.submitMessage = error.error.message;
    }
  }
}
