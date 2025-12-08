import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EstadisticasService {

  private backendUrlModerador = environment.baseUrl + '/estadisticas/moderador';

  constructor(private http: HttpClient) { }

  listarTotalCantidadModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/total-moderador`);
  }

  listarPorcentajePaisModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/pais`);
  }

  listarPorcentajeGeneroModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/genero`);
  }

  listarPorcentajeModulosModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/modulos`);
  }

  listarPorcentajeEstadoModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/estado`);
  }

  listarFechaSemanaModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/fecha/semana`);
  }

  listarDiaSemanaModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/dia/semana`);
  }

  listarFechaMesModerador(): Observable<any> {
    return this.http.get<any>(`${this.backendUrlModerador}/fecha/mes`);
  }

}
