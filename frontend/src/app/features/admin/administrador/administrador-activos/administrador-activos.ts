import { Component } from '@angular/core';
import { Tabla } from "../../../../shared/tabla/tabla";
import { Estados } from '../../../../core/constants/estados';
import { BreakpointObserver } from '@angular/cdk/layout';
import { AdministradorService } from '../../../../core/services/administrador.service';
import { AlertService } from '../../../../core/services/alert.service';
import { UsuarioService } from '../../../../core/services/usuario.service';
import { MatTabsModule } from '@angular/material/tabs';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { RespuestaModel } from '../../../../core/models/respuesta';
import { ModalEliminacion } from '../../../../shared/modal-eliminacion/modal-eliminacion';
import { Pagination } from "../../../../shared/pagination/pagination";
import { Search } from "../../../../shared/search/search";
@Component({
  selector: 'app-administrador-activos',
  imports: [Tabla, CommonModule, MatTabsModule, Pagination, Search],
  templateUrl: './administrador-activos.html',
  styleUrl: './administrador-activos.css',
})
export class AdministradorActivos {

  adminListadoOriginal: any[] = [];
  adminListado: any[] = [];
  datosFiltrados: any[] = [];
  pageActivos = 1;
  get MathActivo() { return Math; }
  columnas = [
    { etiqueta: 'Código', clave: 'codigo' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
  ];

  botonesConfig = {
    desactivar: true,
    suspender: true,
  };

  itemsPerPageActivo = 5;

  constructor(private usuarioService: UsuarioService, private alertService: AlertService, private administradorService: AdministradorService, private breakpointObserver: BreakpointObserver, private dialog: MatDialog) {

  }
  ngOnInit(): void {
    this.listarAdminActivos();

  }
  listarAdminActivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosAdmin(username, Estados.ACTIVO)
      .subscribe(data => {
        this.adminListadoOriginal = data;
        console.log(this.adminListadoOriginal)
        this.adminListado = [...data];
        this.applyPaginationActivos();
      });
  }

  applyPaginationActivos() {
    const start = (this.pageActivos - 1) * this.itemsPerPageActivo;
    this.datosFiltrados = this.adminListado.slice(start, start + this.itemsPerPageActivo);
  }


  desactivar(fila: any): void {
    console.log(fila);
    const username = fila.username || 'Desconocido';

    const isMobile = this.breakpointObserver.isMatched('(max-width: 576px)');

    const dialogEliminar = this.dialog.open(ModalEliminacion, {
      disableClose: true,
      width: isMobile ? '90vw' : '600px',
      height: isMobile ? 'auto' : '290px',
      maxWidth: '95vw',
      maxHeight: '90vh',
      data: {
        fila,
        titulo: 'Desactivar Usuario',
        subtitulo: `¿Deseas desactivar el usuario ${username}?`
      },

    });
    dialogEliminar.afterClosed().subscribe((respuesta: RespuestaModel) => {

      if (respuesta?.boton != 'CONFIRMAR') return;
      this.administradorService.inactivar(fila.codigo).subscribe(result => {
        this.alertService.aceptacion("Se desactivó correctamente el usuario");
        this.listarAdminActivos();

      });
    })
  }
  suspender(fila: any): void {
    console.log(fila);
    const username = fila.username || 'Desconocido';

    const isMobile = this.breakpointObserver.isMatched('(max-width: 576px)');

    const dialogEliminar = this.dialog.open(ModalEliminacion, {
      disableClose: true,
      width: isMobile ? '90vw' : '600px',
      height: isMobile ? 'auto' : '290px',
      maxWidth: '95vw',
      maxHeight: '90vh',
      data: {
        fila,
        titulo: 'Suspender Usuario',
        subtitulo: `¿Deseas suspender el usuario ${username}?`
      },

    });
    console.log(fila.codigo)
    dialogEliminar.afterClosed().subscribe((respuesta: RespuestaModel) => {

      if (respuesta?.boton != 'CONFIRMAR') return;
      this.administradorService.suspender(fila.codigo).subscribe(result => {

        this.alertService.aceptacion("Se suspendio correctamente el usuario");
        this.listarAdminActivos();

      });
    })
  }


  filtrar(text: string) {
    const value = text.toLowerCase().trim();


    this.pageActivos = 1;
    this.adminListado = this.adminListadoOriginal.filter(d =>
      (d.username && d.username.toLowerCase().includes(value)) ||
      (d.correo && d.correo.toLowerCase().includes(value)) ||
      (d.telefono && d.telefono.toLowerCase().includes(value))
    );
    this.applyPaginationActivos();
  }


}
