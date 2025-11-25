import { Component, OnInit } from '@angular/core';
import { TittleComponent } from '../../../../shared/components/tittle/tittle.component';
import { TableComponent } from '../../../../shared/components/table/table.component';
import { PaginationComponent } from '../../../../shared/components/pagination/pagination.component';
import { SearchComponent } from '../../../../shared/components/search/search.component';
import { FilterSelectComponent } from '../../../../shared/components/filter-select/filter-select.component';

@Component({
  selector: 'app-reg-user-admin',
  imports: [TittleComponent, TableComponent, PaginationComponent,SearchComponent,FilterSelectComponent],
  templateUrl: './reg-user-admin.component.html',
  styleUrls: ['./reg-user-admin.component.css'],
})
export class RegUserAdminComponent implements OnInit {

  iconoSeccion = 'fas fa-user-shield';
  tituloSeccion = 'Registro de Admin';

  datosFiltrados: any[] = [];
  datosOriginal: any[] = [];
  get Math() {
    return Math;
  }

  columnas = [
    { etiqueta: 'ID', clave: 'id' },
    { etiqueta: 'Nombre', clave: 'nombre' },
    { etiqueta: 'Correo', clave: 'correo' },
    { etiqueta: 'Teléfono', clave: 'telefono' },
    { etiqueta: 'Rol', clave: 'rol' },
    { etiqueta: 'Estado', clave: 'estado' },
    { etiqueta: 'Fecha Registro', clave: 'fechaRegistro' }
  ];

  datos = [
    { id: 1, nombre: 'Carlos López', correo: 'carlos.lopez@mail.com', telefono: '987654321', rol: 'Administrador', estado: 'Activo', fechaRegistro: '2024-01-10' },
    { id: 2, nombre: 'Luis Torres', correo: 'luis.torres@mail.com', telefono: '912345678', rol: 'Superadmin', estado: 'Inactivo', fechaRegistro: '2024-02-05' },
    { id: 3, nombre: 'María Fernández', correo: 'maria.fernandez@mail.com', telefono: '934567812', rol: 'Moderador', estado: 'Activo', fechaRegistro: '2024-03-18' },
    { id: 4, nombre: 'Jorge Salazar', correo: 'jorge.salazar@mail.com', telefono: '945678123', rol: 'Administrador', estado: 'Activo', fechaRegistro: '2024-04-02' },
    { id: 5, nombre: 'Ana Castillo', correo: 'ana.castillo@mail.com', telefono: '976543210', rol: 'Moderador', estado: 'Suspendido', fechaRegistro: '2024-05-12' },
    { id: 6, nombre: 'Ricardo Pérez', correo: 'ricardo.perez@mail.com', telefono: '987123456', rol: 'Superadmin', estado: 'Activo', fechaRegistro: '2024-06-25' },
    { id: 7, nombre: 'Laura Reyes', correo: 'laura.reyes@mail.com', telefono: '912398745', rol: 'Administrador', estado: 'Activo', fechaRegistro: '2024-07-01' },
    { id: 8, nombre: 'Diego Flores', correo: 'diego.flores@mail.com', telefono: '923456781', rol: 'Moderador', estado: 'Activo', fechaRegistro: '2024-08-17' },
    { id: 9, nombre: 'Carmen Torres', correo: 'carmen.torres@mail.com', telefono: '998123765', rol: 'Administrador', estado: 'Inactivo', fechaRegistro: '2024-09-03' },
    { id: 10, nombre: 'Andrés Ramos', correo: 'andres.ramos@mail.com', telefono: '945612378', rol: 'Superadmin', estado: 'Activo', fechaRegistro: '2024-09-29' },
  ];

  botonesConfig = {
    registrar: () => this.onRegistrar(),
    ver: true,
    actualizar: true,
    eliminar: true,
    imprimir: false
  };

  page = 1;
  itemsPerPage = 5;

  ngOnInit(): void {
    this.datosOriginal = [...this.datos];
    this.applyPagination();
  }

  onRegistrar() { console.log('Registrar'); }
  onVer(item: any) { console.log('Ver:', item); }
  onActualizar(item: any) { console.log('Actualizar:', item); }
  onEliminar(item: any) { console.log('Eliminar:', item); }
  onImprimir(item: any) { console.log('Imprimir:', item); }

  applyPagination() {
    const start = (this.page - 1) * this.itemsPerPage;
    const end = start + this.itemsPerPage;
    this.datosFiltrados = this.datosOriginal.slice(start, end);
  }

  onSearch(text: string) {
    this.datosOriginal = this.datos.filter(d =>
      d.nombre.toLowerCase().includes(text.toLowerCase())
    );
    this.page = 1;
    this.applyPagination();
  }
  filteredData = [...this.datos];
  onPageChange(p: number) {
    this.page = p;
    this.applyPagination();
  }
  roles = ['Administrador', 'Superadmin', 'Moderador', 'Suspendido', 'Activo', 'Inactivo'];

 filtrar(text: string) {
    this.page = 1;

    // Filtra en todos los campos
    this.datosOriginal = this.datos.filter(d =>
      JSON.stringify(d).toLowerCase().includes(text.toLowerCase())
    );

    this.applyPagination();
  }
filtrarPorRol(rol: string) {
  if (!rol) {
    this.datosOriginal = [...this.datos]; // sin filtro
  } else {
    this.datosOriginal = this.datos.filter(d => 
      d.rol.toLowerCase() === rol.toLowerCase()
    );
  }

  this.page = 1;
  this.applyPagination();
}

}
