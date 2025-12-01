import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { RegisterUser } from '../models/register-user.model';

@Injectable({
  providedIn: 'root'
})
export class AdministradorService {

  constructor(private http: HttpClient) { }
  private backendUrl = environment.baseUrl;

  // Listar todos los administradores
  listar(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar`);
  }

  // Obtener administrador por código de usuario
  listarUsuarioCodigo(usuarioCodigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/usuario/codigo/${usuarioCodigo}`);
  }

  // Obtener administrador por username
  obtenerPorUsername(username: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/username/${username}`);
  }

  // Obtener administrador por email
  obtenerPorEmail(email: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/email/${email}`);
  }

  // Obtener administrador por teléfono
  obtenerPorTelefono(telefono: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/telefono/${telefono}`);
  }
  listarActual(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/lista/actual/${codigo}`);
  }


  registrar(request: RegisterUser): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/administrador/registrar`, request);
  }
}
