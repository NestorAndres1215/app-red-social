import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { TittleComponent } from '../../../../shared/components/tittle/tittle.component';
import { FilterSelectComponent } from '../../../../shared/components/filter-select/filter-select.component';
import { HistorialItemComponent } from "../../../../shared/components/item/historial-item/historial-item.component";

import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';

@Component({
  selector: 'app-historial-user-admin',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TittleComponent,
    FilterSelectComponent,
    HistorialItemComponent
  ],
  templateUrl: './historial-user-admin.component.html',
  styleUrls: ['./historial-user-admin.component.css'],
})
export class HistorialUserAdminComponent implements OnInit {

  titulo = 'Historial de Administrador';
  icono = 'fas fa-history';

  modulo: (string | 'TODOS')[] = ['TODOS', 'Registro', 'Publicacion', 'Notificacion', 'LoginFallido', 'Login', 'Comentario', 'Mensaje',
    'Like', 'Seguidores', 'ReporteUsuario', 'ConfiguracionPerfil', 'ReportePublicacion'];

  years: (number | 'TODOS')[] = [];

  historialOriginal: any[] = [];
  historial: any[] = [];

  selectedModulo: string = 'TODOS';
  selectedYear: number | 'TODOS' = 'TODOS';


  constructor(private historiaService: HistorialUsuarioService) { }

  ngOnInit(): void {
    const currentYear = new Date().getFullYear();
    this.years = ['TODOS', ...Array.from({ length: 10 }, (_, i) => currentYear - i)];
    this.selectedYear = currentYear;
    this.cargarHistorial();
  }


  cargarHistorial() {
    const username = localStorage.getItem('username') || '';
    this.historiaService.listarHistorial(username, Estados.ACTIVO)
      .subscribe(data => {
        this.historialOriginal = data;
        this.aplicarFiltros();
      });
  }

  aplicarFiltros() {
    this.historial = this.historialOriginal.filter(item => {
      const moduloItem = String(item.moduloHistorial).toLowerCase();
      const selectedModuloNormalized = String(this.selectedModulo).toLowerCase();

      const coincideModulo = selectedModuloNormalized !== 'todos'
        ? moduloItem === selectedModuloNormalized
        : true;

      const coincideYear = this.selectedYear !== 'TODOS'
        ? new Date(item.fechaHistorial).getFullYear() === Number(this.selectedYear)
        : true;

      console.log(item.moduloHistorial);

      return coincideModulo && coincideYear;
    });
  }


  onModuloChange(value: string | number | null) {
    this.selectedModulo = value !== null ? String(value) : 'TODOS';
    this.aplicarFiltros();
  }

  onYearChange(value: string | number | null) {
    this.selectedYear = value !== null ? (value === 'TODOS' ? 'TODOS' : Number(value)) : 'TODOS';
    this.aplicarFiltros();
  }

}
