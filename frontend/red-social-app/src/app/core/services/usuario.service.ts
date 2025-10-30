import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { API_PATHS } from '../constants/api-paths';
import { HttpClient } from '@angular/common/http';
import { RegisterUser } from '../models/register-user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private backendUrl = environment.backendUrl + API_PATHS.AUTH;

  constructor(private http: HttpClient) { }


  registrar(registerUser: RegisterUser): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/usuario/registrar`, registerUser);
  }

  usuarioPorCodigo(codigo: string): Observable<any> {
    return this.http.get(`${this.backendUrl}/usuario/detalle/${codigo}`);
  }
  
}
