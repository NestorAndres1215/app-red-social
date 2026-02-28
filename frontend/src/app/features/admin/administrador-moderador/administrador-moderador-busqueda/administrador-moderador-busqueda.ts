import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { Search } from "../../../../shared/search/search";
import { Estados } from '../../../../core/constants/estados';
import { Router } from '@angular/router';
import { UsuarioService } from '../../../../core/services/usuario.service';
import { DetalleSearchItem } from "../../../../shared/item/detalle-search-item/detalle-search-item";

@Component({
  selector: 'app-administrador-moderador-busqueda',
  imports: [Titulo, Search, DetalleSearchItem],
  templateUrl: './administrador-moderador-busqueda.html',
  styleUrl: './administrador-moderador-busqueda.css',
})
export class AdministradorModeradorBusqueda {
  adminListadoOriginal: any[] = [];
  adminListado: any[] = [];

  usuariosSeleccionados: any[] = [];

  constructor(private usuarioService: UsuarioService, private router: Router) {}
  
  ngOnInit(): void {
    this.listarAdminActivos();
  }

  listarAdminActivos() {
    const username = localStorage.getItem('username') || '';

    this.usuarioService.listarUsuariosModerador(username, Estados.ACTIVO)
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
    this.router.navigate(['/administrador-moderador/profile', usuario.codigo, usuario.username]);
  }

}
