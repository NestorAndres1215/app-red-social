import { Component, OnInit } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { DetalleSearchComponent } from "../../../../shared/components/item/detalle-search/detalle-search.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buscar-admin',
  standalone: true,
  imports: [TittleComponent, SearchComponent, DetalleSearchComponent],
  templateUrl: './buscar-admin.component.html',
  styleUrl: './buscar-admin.component.css',
})
export class BuscarAdminComponent implements OnInit {

  titulo = 'Busqueda de Administradores';
  icono = 'fa-solid fa-magnifying-glass';

  adminListadoOriginal: any[] = [];
  adminListado: any[] = [];
  usuariosSeleccionados: any[] = [];

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  ngOnInit(): void {
    this.listarAdminActivos();
  }

  listarAdminActivos() {
    const username = localStorage.getItem('username') || '';
    this.usuarioService.listarUsuariosAdmin(username, Estados.ACTIVO)
      .subscribe(data => {
        this.adminListadoOriginal = data;
        this.adminListado = [...data];
        this.usuariosSeleccionados = [...this.adminListado];
      });
  }



  filtrar(text: string) {
    const value = text.toLowerCase().trim();

    this.adminListado = this.adminListadoOriginal.filter(d =>
      (d.username && d.username.toLowerCase().includes(value)) ||
      (d.correo && d.correo.toLowerCase().includes(value)) ||
      (d.telefono && d.telefono.toLowerCase().includes(value))
    );

    this.usuariosSeleccionados = [...this.adminListado];
  }

  verDetalleUsuario(usuario: any) {
    this.router.navigate(['/admin/profile', usuario.codigo, usuario.username]);
  }


}
