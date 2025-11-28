import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private backendUrl = environment.baseUrl;


  constructor(private http: HttpClient) { }

  obtenerMenuNivel1(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/menu/listar/nivel/primero`);
  }

  obtenerMenuNivel2(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/menu/listar/nivel/segundo`);
  }

  obtenerMenuNivel3(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/menu/listar/nivel/tercero`);
  }

  listar(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/menu/listar`);
  }

  listarPorCodigo(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/menu/listar/codigo/${codigo}`);
  }

  obtenerMenusPorDosRoles(codigo: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.backendUrl}/menu/listar/roles/codigo/${codigo}`);
  }

}
