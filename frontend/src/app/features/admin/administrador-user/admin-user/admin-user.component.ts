import { Component } from '@angular/core';
import { TittleComponent } from '../../../../shared/components/tittle/tittle.component';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { TableComponent } from "../../../../shared/components/table/table.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { PaginationComponent } from "../../../../shared/components/pagination/pagination.component";
import { SearchComponent } from "../../../../shared/components/search/search.component";

@Component({
  selector: 'app-admin-user',
  imports: [
    TittleComponent, CommonModule, MatTabsModule,
    TableComponent, PaginationComponent, SearchComponent
  ],
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css'],
})
export class AdminUserComponent {

  constructor(private usuarioService: UsuarioService) {
    this.listarAdminActivos();
    this.listarAdminDesactivos();
  }

  titulo = 'Mantenimiento de Administradores';
  icono = 'fas fa-user-shield';
  activeTab: number = 0;

  columnas = [
    { etiqueta: 'Código', clave: 'codigo' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
  ];

  // ====================
  // ACTIVOS
  // ====================
  adminListadoOriginal: any[] = [];
  adminListado: any[] = [];
  datosFiltrados: any[] = [];
  pageActivos = 1;

  // ====================
  // INACTIVOS
  // ====================
  adminListadoInactivoOriginal: any[] = [];
  adminListadoInactivo: any[] = [];
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

  listarAdminActivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosAdmin(username, Estados.ACTIVO)
      .subscribe(data => {
        this.adminListadoOriginal = data;
        this.adminListado = [...data];
        this.applyPaginationActivos();
      });
  }

  listarAdminDesactivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosAdmin(username, Estados.INACTIVO)
      .subscribe(data => {
        this.adminListadoInactivoOriginal = data;
        this.adminListadoInactivo = [...data];
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
      this.adminListado = this.adminListadoOriginal.filter(d =>
        (d.username && d.username.toLowerCase().includes(value)) ||
        (d.correo && d.correo.toLowerCase().includes(value)) ||
        (d.telefono && d.telefono.toLowerCase().includes(value))
      );
      this.applyPaginationActivos();
    }

    if (this.activeTab === 1) {
      this.pageInactivos = 1;
      this.adminListadoInactivo = this.adminListadoInactivoOriginal.filter(d =>
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
    this.datosFiltrados = this.adminListado.slice(start, start + this.itemsPerPageActivo);
  }

  // ==========================
  // PAGINACIÓN INACTIVOS
  // ==========================
  applyPaginationInactivos() {
    const start = (this.pageInactivos - 1) * this.itemsPerPageDesactivo;
    this.datosFiltradosInactivo = this.adminListadoInactivo.slice(start, start + this.itemsPerPageDesactivo);
  }
  get MathInactivo() { return Math; }
  get MathActivo() { return Math; }
}
