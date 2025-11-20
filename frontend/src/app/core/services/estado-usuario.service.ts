import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EstadoUsuarioService {

  private backendUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  listar(): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/estado-usuario/listar`);
  }

  listarCodigo(codigo: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/estado-usuario/listar/codigo/${codigo}`);
  }



}
