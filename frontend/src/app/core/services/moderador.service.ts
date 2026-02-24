import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { RegisterUser } from '../models/register-user.';
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

  activar(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/moderador/activar/${codigoAdmin}`, {});
  }

  inactivar(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/moderador/inactivar/${codigoAdmin}`, {});
  }

  suspender(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/moderador/suspender/${codigoAdmin}`, {});
  }

  bloquear(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/moderador/bloquear/${codigoAdmin}`, {});
  }

  eliminar(codigoAdmin: string): Observable<any> {
    return this.http.put(`${this.backendUrl}/moderador/eliminar/${codigoAdmin}`, {});
  }

  listarUsuarioCodigo(usuarioCodigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/moderador/listar/codigo/${usuarioCodigo}`);
  }

}
