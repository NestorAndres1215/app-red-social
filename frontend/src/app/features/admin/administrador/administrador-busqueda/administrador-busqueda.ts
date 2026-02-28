import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { Search } from "../../../../shared/search/search";
import { DetalleSearchItem } from "../../../../shared/item/detalle-search-item/detalle-search-item";
import { Router } from '@angular/router';
import { Estados } from '../../../../core/constants/estados';
import { UsuarioService } from '../../../../core/services/usuario.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-administrador-busqueda',
   standalone: true,
  imports: [Titulo, Search, DetalleSearchItem,CommonModule],
  templateUrl: './administrador-busqueda.html',
  styleUrl: './administrador-busqueda.css',
})
export class AdministradorBusqueda {
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
    this.router.navigate(['/administrador/profile', usuario.codigo, usuario.username]);
  }
}
