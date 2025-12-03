import { Component, OnInit } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { PaginationComponent } from "../../../../shared/components/pagination/pagination.component";
import { TableComponent } from "../../../../shared/components/table/table.component";
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { MatTabsModule } from "@angular/material/tabs";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-suspendidos',
  imports: [CommonModule, TittleComponent, PaginationComponent, TableComponent, SearchComponent, MatTabsModule],
  templateUrl: './admin-suspendidos.component.html',
  styleUrl: './admin-suspendidos.component.css',
})
export class AdminSuspendidosComponent implements OnInit {

  titulo = 'Administradores Suspendidos - Inhabilitados';
  icono = 'fa-solid fa-user-slash';


  activeTab: number = 0;
  activosLabel: string = 'Suspendidos';
  desactivadosLabel: string = 'Inabilitados';

  columnas = [
    { etiqueta: 'Código', clave: 'codigo' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
  ];

  constructor(private usuarioService: UsuarioService) {

  }
  ngOnInit(): void {
    this.listarAdminActivos();
    this.listarAdminDesactivos();
  }


  adminListadoOriginal: any[] = [];
  adminListado: any[] = [];
  datosFiltrados: any[] = [];
  pageActivos = 1;


  adminListadoInabilitadoOriginal: any[] = [];
  adminListadoInabilitado: any[] = [];
  datosFiltradosInactivo: any[] = [];
  pageInactivos = 1;

  itemsPerPageSuspendido = 5;
  itemsPerPageInabilitado = 5;
  botonesConfig = {
    registrar: true,
    ver: true,
    actualizar: true,
    eliminar: true,
    imprimir: false
  };

  get MathSuspendidos() {
    return Math;
  }

  get MathInhabilitados() {
    return Math;
  }
  // ==========================
  // PAGINACIÓN ACTIVOS
  // ==========================
  applyPaginationActivos() {
    const start = (this.pageActivos - 1) * this.itemsPerPageSuspendido;
    this.datosFiltrados = this.adminListado.slice(start, start + this.itemsPerPageSuspendido);
  }

  // ==========================
  // PAGINACIÓN INACTIVOS
  // ==========================
  applyPaginationInactivos() {
    const start = (this.pageInactivos - 1) * this.itemsPerPageInabilitado;
    this.datosFiltradosInactivo = this.adminListadoInabilitado.slice(start, start + this.itemsPerPageInabilitado);
  }

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
      this.adminListadoInabilitado = this.adminListadoInabilitadoOriginal.filter(d =>
        (d.username && d.username.toLowerCase().includes(value)) ||
        (d.correo && d.correo.toLowerCase().includes(value)) ||
        (d.telefono && d.telefono.toLowerCase().includes(value))
      );
      this.applyPaginationInactivos();
    }
  }

  listarAdminActivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosAdmin(username, Estados.SUSPENDIDO)
      .subscribe(data => {
        this.adminListadoOriginal = data;
        this.adminListado = [...data];
        this.applyPaginationActivos();
      });
  }

  listarAdminDesactivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosAdmin(username, Estados.INHABILITADO)
      .subscribe(data => {
        this.adminListadoInabilitadoOriginal = data;
        this.adminListadoInabilitado = [...data];
        this.applyPaginationInactivos();
      });
  }

}
