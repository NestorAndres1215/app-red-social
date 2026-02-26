import { BreakpointObserver } from '@angular/cdk/layout';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Estados } from '../../../../core/constants/estados';
import { RespuestaModel } from '../../../../core/models/respuesta';
import { AdministradorService } from '../../../../core/services/administrador.service';
import { AlertService } from '../../../../core/services/alert.service';
import { UsuarioService } from '../../../../core/services/usuario.service';
import { ModalEliminacion } from '../../../../shared/modal-eliminacion/modal-eliminacion';
import { Pagination } from '../../../../shared/pagination/pagination';
import { Search } from '../../../../shared/search/search';
import { Tabla } from '../../../../shared/tabla/tabla';

@Component({
  selector: 'app-administrador-bloqueados',
   imports: [Pagination, Search, Tabla],
  templateUrl: './administrador-bloqueados.html',
  styleUrl: './administrador-bloqueados.css',
})
export class AdministradorBloqueados {

  constructor(
    private usuarioService: UsuarioService, 
    private alertService: AlertService, 
    private administradorService: AdministradorService, 
    private breakpointObserver: BreakpointObserver, 
    private dialog: MatDialog) {}

  ngOnInit(): void {
    this.listarAdminBloqueado();
  }

  columnas = [
    { etiqueta: 'Código', clave: 'codigo' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
  ];

  adminListadoInactivoOriginal: any[] = [];
  adminListadoInactivo: any[] = [];
  datosFiltradosInactivo: any[] = [];
  pageInactivos = 1;
  itemsPerPageDesactivo = 5;

  botonesConfig = {
    activar: true,
  }

  listarAdminBloqueado() {
    const username = localStorage.getItem('username') || '';
    this.usuarioService.listarUsuariosAdmin(username, Estados.BLOQUEADO)
      .subscribe(data => {
        this.adminListadoInactivoOriginal = data;
        this.adminListadoInactivo = [...data];
        this.applyPaginationInactivos();
      });
  }

  filtrar(text: string) {
    const value = text.toLowerCase().trim();
    this.pageInactivos = 1;
    this.adminListadoInactivo = this.adminListadoInactivoOriginal.filter(d =>
      (d.username && d.username.toLowerCase().includes(value)) ||
      (d.correo && d.correo.toLowerCase().includes(value)) ||
      (d.telefono && d.telefono.toLowerCase().includes(value))
    );
    this.applyPaginationInactivos();
  }

  applyPaginationInactivos() {
    const start = (this.pageInactivos - 1) * this.itemsPerPageDesactivo;
    this.datosFiltradosInactivo = this.adminListadoInactivo.slice(start, start + this.itemsPerPageDesactivo);
  }
  
  get MathInactivo() { 
    return Math; 
  }

  activar(fila: any): void {
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
   
    dialogEliminar.afterClosed().subscribe((respuesta: RespuestaModel) => {
      if (respuesta?.boton != 'CONFIRMAR') return;
      this.administradorService.activar(fila.codigo).subscribe(result => {
        this.alertService.aceptacion("Se activo correctamente el usuario");
        this.listarAdminBloqueado();
      });
    })
  }
}
