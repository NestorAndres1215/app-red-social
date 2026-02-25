import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { FilterSelect } from "../../../../shared/filter-select/filter-select";
import { Estados } from '../../../../core/constants/estados';
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HistorialItem } from '../../../../shared/item/historial-item/historial-item';

@Component({
  selector: 'app-administrador-historial',
  imports: [Titulo, FilterSelect,    CommonModule, HistorialItem,
    ReactiveFormsModule,],
  templateUrl: './administrador-historial.html',
  styleUrl: './administrador-historial.css',
})
export class AdministradorHistorial {
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
