import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  getCurrentUser() {
    const token = localStorage.getItem('jwt');

    if (!token) {
      return throwError(() => new Error('No hay token disponible'));
    }
    console.log(token)
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${this.backendUrl}/auth/actual-usuario`, { headers });
  }
  generateToken(loginData: any) {
    return this.http.post(`${this.backendUrl}/auth/generate-token`, loginData);
  }



  isLoggedIn() {
    let tokenStr = localStorage.getItem('jwt');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }
  get token(): string | null {
    return localStorage.getItem('jwt');
  }
  setToken(token: string) {
    localStorage.setItem('jwt', token);
  }
}
