import { Component } from '@angular/core';
import { TittleComponent } from '../../../../shared/components/tittle/tittle.component';
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { TableComponent } from "../../../../shared/components/table/table.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { PaginationComponent } from "../../../../shared/components/pagination/pagination.component";
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { ModalEliminacionComponent } from '../../../../shared/components/modal/modal-eliminacion/modal-eliminacion.component';
import { MatDialog } from '@angular/material/dialog';
import { BreakpointObserver } from '@angular/cdk/layout';
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

  constructor(private usuarioService: UsuarioService, private breakpointObserver: BreakpointObserver, private dialog: MatDialog) {
    this.listarAdminActivos();
    this.listarAdminDesactivos();
  }

  titulo = 'Mantenimiento de Administradores';
  icono = 'fas fa-user-shield';
  activeTab: number = 0;
  activosLabel: string = 'Activados';
  desactivadosLabel: string = 'Desactivados';


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
    ver: false,
    actualizar: false,
    desactivar: true,
    activar: false,       // opcional, mostrar según necesidad
    suspender: false,
    inhabilitar: false,
    bloquear: false,
    imprimir: false,
    cancelar: false
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


 desactivar(fila: any): void {
  const nombreUsuario = fila.Nombre || 'Desconocido';

  const isMobile = this.breakpointObserver.isMatched('(max-width: 576px)');

  const dialogEliminar = this.dialog.open(ModalEliminacionComponent, {
    disableClose: true,
    width: isMobile ? '90vw' : '600px',
    height: isMobile ? 'auto' : '290px',
    maxWidth: '95vw',
    maxHeight: '90vh',
    data: {
      fila,
      titulo: 'Desactivar Usuario',
      subtitulo: `¿Deseas desactivar el usuario ${nombreUsuario}?`
    },
  });
}
}