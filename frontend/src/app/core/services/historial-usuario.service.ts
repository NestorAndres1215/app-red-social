import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistorialUsuario } from '../models/historial-usuario';


@Injectable({
  providedIn: 'root',
})
export class HistorialUsuarioService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  listar(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/historial-usuario/listar`);
  }

  listarCodigo(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/historial-usuario/listar/codigo/${codigo}`);
  }

  listarHistorial(username: string, estado: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/historial-usuario/listar/historial/${username}/${estado}`);
  }

  listarHistorialModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/historial-usuario/listar/historial/moderador`);
  }

  registrar(body: HistorialUsuario): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/historial-usuario/registrar`, body);
  }

  inactivar(codigo: string): Observable<any> {
    return this.http.delete<any>(`${this.backendUrl}/historial-usuario/inactivar/estado/${codigo}`);
  }
}
