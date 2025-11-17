import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RolService {

  private backendUrl = environment.baseUrl;

  constructor(private http: HttpClient) {}

  
}