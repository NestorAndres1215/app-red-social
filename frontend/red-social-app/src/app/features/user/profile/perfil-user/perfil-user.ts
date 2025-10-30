import { Component } from '@angular/core';
import { SidebarUser } from "../../../../shared/layout/sidebar-user/sidebar-user";
import { NavbarUser } from "../../../../shared/layout/navbar-user/navbar-user";
import { CommonModule } from '@angular/common';
import { GoogleService } from '../../../../core/services/google.service';
import { UsuarioService } from '../../../../core/services/usuario.service';
import { ActivatedRoute } from '@angular/router';
import { obtenerImagenPerfil } from '../../../../core/utils/validators.utils';
import { MatTabsModule } from '@angular/material/tabs';

@Component({
  selector: 'app-perfil-user',
  standalone: true,
  imports: [NavbarUser, CommonModule, MatTabsModule],
  templateUrl: './perfil-user.html',
  styleUrl: './perfil-user.css'
})
export class PerfilUser {
  //backgroundUrl: string = 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80';
  isNavActive: boolean = false;
  isLoggedIn: boolean = true;
  usuario: any = null;
  displayName: string = '';
  backgroundUrl: string = '';
  nombreCompleto: String = '';
  activeTab: number = 0;

  constructor(private route: ActivatedRoute, private googleService: GoogleService, private usuarioService: UsuarioService) { } // nombre consistente

  ngOnInit(): void {
    const codigo = this.route.snapshot.paramMap.get('codigo');

    if (codigo) {
      this.listarActual(codigo);
    }
  }

  toggleNav(): void {
    this.isNavActive = !this.isNavActive;
  }

  logout(): void {
    this.isLoggedIn = false;
    this.usuario = null;
    this.displayName = '';
  }
  listarActual(codigo: string) {
    this.usuarioService.usuarioPorCodigo(codigo).subscribe({
      next: (data) => {
        this.usuario = data;
        this.backgroundUrl = this.usuario.photoUrl
        this.nombreCompleto = `${this.usuario?.nombre || ''} ${this.usuario?.apellido || ''}`.trim();
        this.displayName = this.usuario.username?.trim() || this.usuario.email;
      },
      error: (error) => {
        console.log('Error al obtener usuario:', error);
      }
    });
  }

  mostrarImagen(usuario: any): string | null {
    return obtenerImagenPerfil(usuario?.perfil);
  }
  tabs = [
    { label: 'Publicaciones' },
    { label: 'Información' },
    { label: 'Seguidores' },
    { label: 'Seguidos' },
    { label: 'Fotos' },
    { label: 'Videos' }
  ];

  onTabChange(event: any) {
    this.activeTab = event.index;
  }
}
