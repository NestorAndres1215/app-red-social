import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private backendUrl = environment.baseUrl;

  public loginStatusSubjec = new Subject<boolean>();
  constructor(
    private router: Router,
    private http: HttpClient
  ) { }

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

  loginWithGoogle(): void {
      window.location.href = 'http://localhost:8090/red-social-app/auth-service/api/v1/oauth2/authorization/google';
  }


  handleTokenFromUrl(): void {

    const params = new URLSearchParams(window.location.search);
    const token = params.get('token');

    if (token) {
      this.setToken(token);
      this.router.navigate(['/inicio'], { replaceUrl: true });
    } else {
      console.warn('No se encontró token en la URL');
    }

  }


  logout(): void {
    localStorage.removeItem('jwt');
    this.router.navigate(['/auth/login']);
  }

  isAuthenticated(): boolean {
    return this.token !== null;
  }
}
