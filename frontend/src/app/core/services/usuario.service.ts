import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { RegisterUser } from '../models/register-user.';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  listar(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar`);
  }

  listarCodigo(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/codigo/${codigo}`);
  }

  listarActual(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/usuario/actual/${codigo}`);
  }
  listarUsername(username: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/username/${username}`);
  }

  listarEmail(email: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/email/${email}`);
  }

  listarTelefono(telefono: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/telefono/${telefono}`);
  }

  listarUsuariosNormal(username: string, estado: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/usuarios/normal/${username}/${estado}`);
  }

  listarUsuariosAdmin(username: string, estado: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/usuarios/administradores/${username}/${estado}`);
  }

  listarUsuariosModerador(username: string, estado: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/usuario/listar/usuarios/moderador/${username}/${estado}`);
  }
  
  registrar(request: RegisterUser): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/usuario/registrar`, request);
  }

}
