import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { CommonModule } from '@angular/common';
import { TableComponent } from '../../../../shared/components/table/table.component';
import { PaginationComponent } from '../../../../shared/components/pagination/pagination.component';
import { MatTabsModule } from '@angular/material/tabs';

@Component({
  selector: 'app-mod-user.component',
  imports: [
    TittleComponent, CommonModule, MatTabsModule,
    TableComponent, PaginationComponent, SearchComponent
  ],
  templateUrl: './mod-user.component.html',
  styleUrl: './mod-user.component.css',
})
export class ModUserComponent {
  titulo = 'Mantenimiento de Moderadores';
  icono = 'fas fa-user-check';

  activeTab: number = 0;
  activosLabel: string = 'Activados';
  desactivadosLabel: string = 'Desactivados';

  constructor(private usuarioService: UsuarioService) {
    this.listarModeradorActivos()
    this.listarModeradorDesactivos()
  }
  columnas = [
    { etiqueta: 'Código', clave: 'codigo' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
  ];


  // ====================
  // ACTIVOS
  // ====================
  moderadorListadoOriginal: any[] = [];
  moderadorListado: any[] = [];
  datosFiltrados: any[] = [];
  pageActivos = 1;

  // ====================
  // INACTIVOS
  // ====================
  moderadorListadoInactivoOriginal: any[] = [];
  moderadorListadoInactivo: any[] = [];
  datosFiltradosInactivo: any[] = [];
  pageInactivos = 1;

  itemsPerPageActivo = 5;
  itemsPerPageDesactivo = 5;

  botonesConfig = {
    registrar: true,
    ver: true,
    actualizar: true,
    eliminar: true,
    imprimir: false
  };

  listarModeradorActivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosModerador("string", Estados.ACTIVO)
      .subscribe(data => {
        this.moderadorListadoOriginal = data;
        this.moderadorListado = [...data];
        this.applyPaginationActivos();
      });
  }

  listarModeradorDesactivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosModerador(username, Estados.INACTIVO)
      .subscribe(data => {
        this.moderadorListadoInactivoOriginal = data;
        this.moderadorListadoInactivo = [...data];
        this.applyPaginationInactivos();
      });
  }
  // ==========================
  // FILTRO
  // ==========================
  filtrar(text: string) {
    const value = text.toLowerCase().trim();

    if (this.activeTab === 0) {
      this.pageActivos = 1;
      this.moderadorListado = this.moderadorListadoOriginal.filter(d =>
        (d.username && d.username.toLowerCase().includes(value)) ||
        (d.correo && d.correo.toLowerCase().includes(value)) ||
        (d.telefono && d.telefono.toLowerCase().includes(value))
      );
      this.applyPaginationActivos();
    }

    if (this.activeTab === 1) {
      this.pageInactivos = 1;
      this.moderadorListadoInactivo = this.moderadorListadoInactivoOriginal.filter(d =>
        (d.username && d.username.toLowerCase().includes(value)) ||
        (d.correo && d.correo.toLowerCase().includes(value)) ||
        (d.telefono && d.telefono.toLowerCase().includes(value))
      );
      this.applyPaginationInactivos();
    }
  }

  // ==========================
  // PAGINACIÓN ACTIVOS
  // ==========================
  applyPaginationActivos() {
    const start = (this.pageActivos - 1) * this.itemsPerPageActivo;
    this.datosFiltrados = this.moderadorListado.slice(start, start + this.itemsPerPageActivo);
  }

  // ==========================
  // PAGINACIÓN INACTIVOS
  // ==========================
  applyPaginationInactivos() {
    const start = (this.pageInactivos - 1) * this.itemsPerPageDesactivo;
    this.datosFiltradosInactivo = this.moderadorListadoInactivo.slice(start, start + this.itemsPerPageDesactivo);
  }
  get MathInactivo() { return Math; }
  get MathActivo() { return Math; }
}
