import { Component } from '@angular/core';
import { Search } from "../../../../shared/search/search";
import { Pagination } from "../../../../shared/pagination/pagination";
import { Tabla } from "../../../../shared/tabla/tabla";
import { ModalEliminacion } from '../../../../shared/modal-eliminacion/modal-eliminacion';
import { UsuarioService } from '../../../../core/services/usuario.service';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatDialog } from '@angular/material/dialog';
import { Estados } from '../../../../core/constants/estados';
import { RespuestaModel } from '../../../../core/models/respuesta';
import { AlertService } from '../../../../core/services/alert.service';
import { ModeradorService } from '../../../../core/services/moderador.service';

@Component({
  selector: 'app-administrador-moderador-inactivos',
  imports: [Search, Pagination, Tabla],
  templateUrl: './administrador-moderador-inactivos.html',
  styleUrl: './administrador-moderador-inactivos.css',
})
export class AdministradorModeradorInactivos {

  constructor(private usuarioService: UsuarioService, private alertService: AlertService, private moderadorService: ModeradorService, private breakpointObserver: BreakpointObserver, private dialog: MatDialog) {

  }
  ngOnInit(): void {
    this.listarModeradorDesactivos()
  }

  columnas = [
    { etiqueta: 'Código', clave: 'codigo' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
  ];


  moderadorListadoInactivoOriginal: any[] = [];
  moderadorListadoInactivo: any[] = [];
  datosFiltradosInactivo: any[] = [];
  pageInactivos = 1;

  itemsPerPageDesactivo = 5;


  botonesConfig2 = {
    activar: true,
  };



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


    this.pageInactivos = 1;
    this.moderadorListadoInactivo = this.moderadorListadoInactivoOriginal.filter(d =>
      (d.username && d.username.toLowerCase().includes(value)) ||
      (d.correo && d.correo.toLowerCase().includes(value)) ||
      (d.telefono && d.telefono.toLowerCase().includes(value))
    );
    this.applyPaginationInactivos();

  }



  applyPaginationInactivos() {
    const start = (this.pageInactivos - 1) * this.itemsPerPageDesactivo;
    this.datosFiltradosInactivo = this.moderadorListadoInactivo.slice(start, start + this.itemsPerPageDesactivo);
  }

  get MathInactivo() { return Math; }


  activar(fila: any): void {
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
        titulo: 'Activar Usuario',
        subtitulo: `¿Deseas activar el usuario ${username}?`
      },

    });
    console.log(fila.codigo)
    dialogEliminar.afterClosed().subscribe((respuesta: RespuestaModel) => {

      if (respuesta?.boton != 'CONFIRMAR') return;
      this.moderadorService.activar(fila.codigo).subscribe(result => {

        this.alertService.aceptacion("Se activo correctamente el usuario");
     
        this.listarModeradorDesactivos();
      });
    })
  }
}
