import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { TableComponent } from "../../../../shared/components/table/table.component";
import { PaginationComponent } from "../../../../shared/components/pagination/pagination.component";

@Component({
  selector: 'app-cuentas-bloqueadas-admin',
  imports: [TittleComponent, SearchComponent, TableComponent, PaginationComponent],
  templateUrl: './cuentas-bloqueadas-admin.component.html',
  styleUrl: './cuentas-bloqueadas-admin.component.css',
})
export class CuentasBloqueadasAdminComponent {
  titulo = 'Cuentas bloqueadas';
  icono = 'fa-solid fa-lock';

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
  }
  adminListadoOriginal: any[] = [];
  adminListado: any[] = [];
  datosFiltrados: any[] = [];
  pageActivos = 1;
  itemsPerPageSuspendido = 5;

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


  applyPaginationActivos() {
    const start = (this.pageActivos - 1) * this.itemsPerPageSuspendido;
    this.datosFiltrados = this.adminListado.slice(start, start + this.itemsPerPageSuspendido);
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

  listarAdminActivos() {
      const username = localStorage.getItem('username') || '';
  
      this.usuarioService.listarUsuariosAdmin(username, Estados.BLOQUEADO)
        .subscribe(data => {
          this.adminListadoOriginal = data;
          this.adminListado = [...data];
          this.applyPaginationActivos();
        });
    }

}
