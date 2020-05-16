import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authenticate(data) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.httpClient.post('http://localhost:8080/api/v1/auth/login', data, httpOptions);
  }

  authenticateUserRegister(data) {
    // write code here to connect to server with username and password
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.httpClient.post('http://localhost:8080/api/v1/auth/register', data, httpOptions);
  }

  setBearerToken(token) {
    localStorage.setItem('bearerToken', token);
  }
  setUserId(userId) {
    localStorage.setItem('userId', userId);
  }
  getUserId() {
    return localStorage.getItem('userId');
  }
  getBearerToken() {
    return localStorage.getItem('bearerToken');
  }

  isUserLoggedIn() {
    let user =this.getUserId();
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    localStorage.clear();
    sessionStorage.removeItem('username')
  }

}
