import { Component, OnInit } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { CommonModule } from '@angular/common';
import { TableComponent } from '../../../../shared/components/table/table.component';
import { PaginationComponent } from '../../../../shared/components/pagination/pagination.component';
import { MatTabsModule } from '@angular/material/tabs';
import { ModeradorService } from '../../../../core/services/moderador.service';
import { MatDialog } from '@angular/material/dialog';
import { BreakpointObserver } from '@angular/cdk/layout';
import { ModalEliminacionComponent } from '../../../../shared/components/modal/modal-eliminacion/modal-eliminacion.component';
import { AlertService } from '../../../../core/services/alert.service';
import { RespuestaModel } from '../../../../core/models/respuesta.model';

@Component({
  selector: 'app-mod-user.component',
  imports: [
    TittleComponent, CommonModule, MatTabsModule,
    TableComponent, PaginationComponent, SearchComponent
  ],
  templateUrl: './mod-user.component.html',
  styleUrl: './mod-user.component.css',
})
export class ModUserComponent implements OnInit {
  titulo = 'Mantenimiento de Moderadores';
  icono = 'fas fa-user-check';

  activeTab: number = 0;
  activosLabel: string = 'Activados';
  desactivadosLabel: string = 'Desactivados';

  constructor(private usuarioService: UsuarioService, private alertService: AlertService, private moderadorService: ModeradorService,private breakpointObserver: BreakpointObserver, private dialog: MatDialog) {

  }
  ngOnInit(): void {
    this.listarModeradorActivos()
    this.listarModeradorDesactivos()
  }

  columnas = [
    { etiqueta: 'Código', clave: 'codigo' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
  ];

  moderadorListadoOriginal: any[] = [];
  moderadorListado: any[] = [];
  datosFiltrados: any[] = [];
  pageActivos = 1;

  moderadorListadoInactivoOriginal: any[] = [];
  moderadorListadoInactivo: any[] = [];
  datosFiltradosInactivo: any[] = [];
  pageInactivos = 1;

  itemsPerPageActivo = 5;
  itemsPerPageDesactivo = 5;

  botonesConfig = {
    desactivar: true,
    suspender: true,
  };

  botonesConfig2 = {
    activar: true,     
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

  applyPaginationActivos() {
    const start = (this.pageActivos - 1) * this.itemsPerPageActivo;
    this.datosFiltrados = this.moderadorListado.slice(start, start + this.itemsPerPageActivo);
  }

  applyPaginationInactivos() {
    const start = (this.pageInactivos - 1) * this.itemsPerPageDesactivo;
    this.datosFiltradosInactivo = this.moderadorListadoInactivo.slice(start, start + this.itemsPerPageDesactivo);
  }
  get MathInactivo() { return Math; }
  get MathActivo() { return Math; }


  desactivar(fila: any): void {
    console.log(fila);
    const username = fila.username || 'Desconocido';

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
        subtitulo: `¿Deseas desactivar el usuario ${username}?`
      },

    });
    console.log(fila.codigo)
    dialogEliminar.afterClosed().subscribe((respuesta: RespuestaModel) => {

      if (respuesta?.boton != 'CONFIRMAR') return;
      this.moderadorService.inactivar(fila.codigo).subscribe(result => {

        this.alertService.aceptacion("Se desactivó correctamente el usuario");
        this.listarModeradorActivos();
        this.listarModeradorDesactivos();
      });
    })
  }
  suspender(fila: any): void {
    console.log(fila);
    const username = fila.username || 'Desconocido';

    const isMobile = this.breakpointObserver.isMatched('(max-width: 576px)');

    const dialogEliminar = this.dialog.open(ModalEliminacionComponent, {
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
      this.moderadorService.suspender(fila.codigo).subscribe(result => {

        this.alertService.aceptacion("Se suspendio correctamente el usuario");
        this.listarModeradorActivos();
        this.listarModeradorDesactivos();
      });
    })
  }
  activar(fila: any): void {
    console.log(fila);
    const username = fila.username || 'Desconocido';

    const isMobile = this.breakpointObserver.isMatched('(max-width: 576px)');

    const dialogEliminar = this.dialog.open(ModalEliminacionComponent, {
      disableClose: true,
      width: isMobile ? '90vw' : '600px',
      height: isMobile ? 'auto' : '290px',
      maxWidth: '95vw',
      maxHeight: '90vh',
      data: {
        fila,
        titulo: 'Activar Usuario',
        subtitulo: `¿Deseas activar el usuario ${username}?`
      },

    });
    console.log(fila.codigo)
    dialogEliminar.afterClosed().subscribe((respuesta: RespuestaModel) => {

      if (respuesta?.boton != 'CONFIRMAR') return;
      this.moderadorService.activar(fila.codigo).subscribe(result => {

        this.alertService.aceptacion("Se activo correctamente el usuario");
        this.listarModeradorActivos();
        this.listarModeradorDesactivos();
      });
    })
  }
}
