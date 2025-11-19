import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistorialUsuarioModel } from '../models/historial-usuario-model';

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

  registrar(body: HistorialUsuarioModel): Observable<HistorialUsuarioModel> {
    return this.http.post<HistorialUsuarioModel>(`${this.backendUrl}/historial-usuario/registrar`, body);
  }

  inactivar(codigo: string): Observable<any> {
    return this.http.delete<any>(`${this.backendUrl}/historial-usuario/inactivar/estado/${codigo}`);
  }
}
