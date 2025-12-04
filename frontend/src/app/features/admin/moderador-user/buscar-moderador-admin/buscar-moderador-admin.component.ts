import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { SearchComponent } from "../../../../shared/components/search/search.component";
import { DetalleSearchComponent } from "../../../../shared/components/item/detalle-search/detalle-search.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { Router } from '@angular/router';
import { Estados } from '../../../../core/constants/estados.contants';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-buscar-moderador-admin',
  imports: [TittleComponent, SearchComponent, DetalleSearchComponent, CommonModule],
  templateUrl: './buscar-moderador-admin.component.html',
  styleUrl: './buscar-moderador-admin.component.css',
})
export class BuscarModeradorAdminComponent {
  titulo = 'Busqueda de Moderadores';
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
    this.router.navigate(['/mod-admin/profile', usuario.codigo, usuario.username]);
  }

}
