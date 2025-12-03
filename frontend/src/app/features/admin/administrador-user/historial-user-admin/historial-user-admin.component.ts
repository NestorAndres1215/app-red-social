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
export class HistorialUserAdminComponent {

  titulo = 'Historial de Administrador';
  icono = 'fas fa-history';

  modulo: (string | 'TODOS')[] = ['TODOS', 'Registro', 'Publicacion', 'Notificacion', 'LoginFallido', 'Login', 'Comentario', 'Mensaje',
    'Like', 'Seguidores', 'ReporteUsuario', 'ConfiguracionPerfil', 'ReportePublicacion'];

  years: (number | 'TODOS')[] = [];

  historialOriginal: any[] = [];
  historial: any[] = [];

  selectedModulo: string = 'TODOS';
  selectedYear: number | 'TODOS';

  constructor(private historiaService: HistorialUsuarioService) {
    const currentYear = new Date().getFullYear();

    // Creamos lista de años con "TODOS" al inicio
    this.years = ['TODOS', ...Array.from({ length: 10 }, (_, i) => currentYear - i)];

    // Año actual por defecto
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
      // Normalizar a minúsculas para evitar problemas de mayúsculas/minúsculas
      const moduloItem = String(item.moduloHistorial).toLowerCase();
      const selectedModuloNormalized = String(this.selectedModulo).toLowerCase();

      const coincideModulo = selectedModuloNormalized !== 'todos'
        ? moduloItem === selectedModuloNormalized
        : true;

      const coincideYear = this.selectedYear !== 'TODOS'
        ? new Date(item.fechaHistorial).getFullYear() === Number(this.selectedYear)
        : true;

      console.log(item.moduloHistorial); // para depuración

      return coincideModulo && coincideYear;
    });
  }


  onModuloChange(value: string | number | null) {
    console.log(value)
    // Si es null, usamos 'TODOS'
    this.selectedModulo = value !== null ? String(value) : 'TODOS';
    console.log(this.selectedModulo)
    this.aplicarFiltros();
  }

  onYearChange(value: string | number | null) {
    // Si es null, usamos 'TODOS'
    this.selectedYear = value !== null ? (value === 'TODOS' ? 'TODOS' : Number(value)) : 'TODOS';
    this.aplicarFiltros();
  }


  onViewDetail(item: any) { console.log('Ver detalle', item); }
  onUpdate(item: any) { console.log('Actualizar', item); }
  onDelete(item: any) { console.log('Eliminar', item); }
}
