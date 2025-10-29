import { Injectable } from '@angular/core';
import { API_PATHS } from '../constants/api-paths';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { GoogleAuth } from '../models/google-auth.model';

@Injectable({
  providedIn: 'root'
})
export class GoogleService {
  private backendUrl = environment.backendUrl + API_PATHS.AUTH;

  constructor(private http: HttpClient) { }

  handleAuthCallback(code: string) {
    return this.http.post<{ jwt: string }>(`${this.backendUrl}/callback`, {
      code,
    });
  }
  loginWithCode(code: string): Observable<GoogleAuth> {
    return this.http.post<GoogleAuth>(
      `${this.backendUrl}/google/loginWithGoogle`,
      { code },
      { headers: { 'Content-Type': 'application/json' } }
    );
  }

  login() {
    this.http.get<{ url: string }>(`${this.backendUrl}/google/login-url`).subscribe({
      next: (response) => {
        console.log(response)
        window.location.href = response.url; // Redirige a Google
      },
      error: (err) => {
        console.error('Error obteniendo URL de login', err);
      },
    });
  }
  getCurrentUser() {
    const token = localStorage.getItem('jwt');

    if (!token) {
      return throwError(() => new Error('No hay token disponible'));
    }
    console.log(token)
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${this.backendUrl}/auth/actual-usuario`, { headers });
  }
  isLoggedIn() {
    let tokenStr = localStorage.getItem('jwt');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }

}
