import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RolService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  listar(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/rol/listar`);
  }

  listarCodigo(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/rol/listar/codigo/${codigo}`);
  }

  listarNombre(nombre: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/rol/listar/nombre/${nombre}`);
  }
}