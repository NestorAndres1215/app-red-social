import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }


  registrarLogueo(username: string) {
    return this.http.post(`${this.backendUrl}/login/ultimo-login/${username}`, {});
  }

  bloquear(username: string): Observable<any> {
    return this.http.put<any>(`${this.backendUrl}/login/${username}/bloquear`, {});
  }

}
