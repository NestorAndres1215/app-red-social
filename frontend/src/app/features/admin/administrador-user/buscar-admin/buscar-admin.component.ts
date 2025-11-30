import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { DetalleSearchComponent } from "../../../../shared/components/detalle-search/detalle-search.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';

@Component({
  selector: 'app-buscar-admin',
  standalone: true,
  imports: [TittleComponent, SearchComponent, DetalleSearchComponent],
  templateUrl: './buscar-admin.component.html',
  styleUrl: './buscar-admin.component.css',
})
export class BuscarAdminComponent {

  titulo = 'Busqueda de Administradores';
  icono = 'fa-solid fa-magnifying-glass';

  adminListadoOriginal: any[] = [];
  adminListado: any[] = [];

  // LISTA COMPLETA para mostrar varios
  usuariosSeleccionados: any[] = [];

  constructor(private usuarioService: UsuarioService) {
    this.listarAdminActivos();
  }

  listarAdminActivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosAdmin(username, Estados.ACTIVO)
      .subscribe(data => {
        this.adminListadoOriginal = data;
        this.adminListado = [...data];

        // 👉 MOSTRAR TODOS LOS USUARIOS
        this.usuariosSeleccionados = [...this.adminListado];
      });
  }

  // FILTRO
  filtrar(text: string) {
    const value = text.toLowerCase().trim();

    this.adminListado = this.adminListadoOriginal.filter(d =>
      (d.username && d.username.toLowerCase().includes(value)) ||
      (d.correo && d.correo.toLowerCase().includes(value)) ||
      (d.telefono && d.telefono.toLowerCase().includes(value))
    );

    // 👉 MOSTRAR TODOS LOS USUARIOS FILTRADOS
    this.usuariosSeleccionados = [...this.adminListado];
  }

  // Evento desde el hijo
  verDetalleUsuario(usuario: any) {
    console.log("Detalle del usuario:", usuario);
  }

}
