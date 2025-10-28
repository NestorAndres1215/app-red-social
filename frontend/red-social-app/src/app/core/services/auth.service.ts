import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { API_PATHS } from '../constants/api-paths';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private backendUrl = environment.backendUrl + API_PATHS.AUTH;

  constructor(private http: HttpClient) { }

  generateToken(loginData: any) {
    return this.http.post(`${this.backendUrl}/auth/generate-token`, loginData);
  }
  getCurrentUser() {
    const token = localStorage.getItem('jwt');
    console.log(token)
    if (token) {
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      console.log(headers)
      return this.http.get(`${this.backendUrl}/auth/actual-usuario`, { headers });
    } else {
      // Si no tienes el token, lanza un error o maneja de otra manera
      return this.http.get(`${this.backendUrl}/auth/actual-usuario`);  // O puedes manejarlo de otra forma
    }
  }



  // =========================
  // Obtener token
  // =========================
  get token(): string | null {
    return localStorage.getItem('jwt');
  }

  // =========================
  // Guardar token
  // =========================
  setToken(token: string) {
    localStorage.setItem('jwt', token);
  }
}
