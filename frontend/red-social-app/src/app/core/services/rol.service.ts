import { Injectable } from '@angular/core';
import { API_PATHS } from '../constants/api-paths';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RolModel } from '../models/rol.model';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  private backendUrl = environment.backendUrl + API_PATHS.AUTH;

  constructor(private http: HttpClient) { }

  listar(): Observable<any[]> {
    return this.http.get<any[]>(`${this.backendUrl}/rol/listar`);
  }

  listarCodigo(id: string): Observable<any> {
    return this.http.get<any>(`${this.backendUrl}/rol/listarId/${id}`);
  }

  registrar(role: RolModel): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}/rol/registrar`, role);
  }

  actualizar(role: RolModel): Observable<any> {
    return this.http.put<any>(`${this.backendUrl}/rol/actualizar`, role);
  }
}
