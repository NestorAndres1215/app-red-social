import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CodigoVerificacionService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  verificarCorreo(username: string) {
    return this.http.post<any>(`${this.backendUrl}/codigo-verificacion/verificar/correo/${username}`, {});
  }


}
