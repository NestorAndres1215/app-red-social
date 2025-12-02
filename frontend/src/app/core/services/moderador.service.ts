import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RegisterUser } from '../models/register-user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ModeradorService {

  constructor(private http: HttpClient) { }
  private backendUrl = environment.baseUrl;
  registrar(request: RegisterUser): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/moderador/registrar`, request);
  }
}
