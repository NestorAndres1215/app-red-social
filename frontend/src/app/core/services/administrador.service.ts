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

  listar(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar`);
  }

  listarUsuarioCodigo(usuarioCodigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/usuario/codigo/${usuarioCodigo}`);
  }

  obtenerPorUsername(username: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/username/${username}`);
  }

  obtenerPorEmail(email: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/email/${email}`);
  }

  obtenerPorTelefono(telefono: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/listar/telefono/${telefono}`);
  }

  listarActual(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/administrador/lista/actual/${codigo}`);
  }

  registrar(request: RegisterUser): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/administrador/registrar`, request);
  }

  activar(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/administrador/activar/${codigoAdmin}`, {});
  }

  inactivar(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/administrador/inactivar/${codigoAdmin}`, {});
  }

  suspender(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/administrador/suspender/${codigoAdmin}`, {});
  }

  bloquear(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/administrador/bloquear/${codigoAdmin}`, {});
  }

  eliminar(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/administrador/eliminar/${codigoAdmin}`, {});
  }
}
