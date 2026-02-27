import { Component } from '@angular/core';
import { Search } from "../../../../shared/search/search";
import { Tabla } from "../../../../shared/tabla/tabla";
import { Pagination } from "../../../../shared/pagination/pagination";
import { RespuestaModel } from '../../../../core/models/respuesta';
import { ModalEliminacion } from '../../../../shared/modal-eliminacion/modal-eliminacion';
import { Estados } from '../../../../core/constants/estados';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatDialog } from '@angular/material/dialog';
import { AlertService } from '../../../../core/services/alert.service';
import { ModeradorService } from '../../../../core/services/moderador.service';
import { UsuarioService } from '../../../../core/services/usuario.service';

@Component({
  selector: 'app-administrador-moderador-activos',
  imports: [Search, Tabla, Pagination],
  templateUrl: './administrador-moderador-activos.html',
  styleUrl: './administrador-moderador-activos.css',
})
export class AdministradorModeradorActivos {


  constructor(private usuarioService: UsuarioService, private alertService: AlertService, private moderadorService: ModeradorService, private breakpointObserver: BreakpointObserver, private dialog: MatDialog) {

  }
  ngOnInit(): void {
    this.listarModeradorActivos()

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


  itemsPerPageActivo = 5;

  botonesConfig = {
    desactivar: true,
    suspender: true,
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


  filtrar(text: string) {
    const value = text.toLowerCase().trim();


    this.pageActivos = 1;
    this.moderadorListado = this.moderadorListadoOriginal.filter(d =>
      (d.username && d.username.toLowerCase().includes(value)) ||
      (d.correo && d.correo.toLowerCase().includes(value)) ||
      (d.telefono && d.telefono.toLowerCase().includes(value))
    );
    this.applyPaginationActivos();

  }

  applyPaginationActivos() {
    const start = (this.pageActivos - 1) * this.itemsPerPageActivo;
    this.datosFiltrados = this.moderadorListado.slice(start, start + this.itemsPerPageActivo);
  }

  get MathActivo() { return Math; }


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
    console.log(fila.codigo)
    dialogEliminar.afterClosed().subscribe((respuesta: RespuestaModel) => {

      if (respuesta?.boton != 'CONFIRMAR') return;
      this.moderadorService.inactivar(fila.codigo).subscribe(result => {

        this.alertService.aceptacion("Se desactivó correctamente el usuario");
        this.listarModeradorActivos();

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
      this.moderadorService.suspender(fila.codigo).subscribe(result => {
        this.alertService.aceptacion("Se suspendio correctamente el usuario");
        this.listarModeradorActivos();
      });
    })
  }

}
