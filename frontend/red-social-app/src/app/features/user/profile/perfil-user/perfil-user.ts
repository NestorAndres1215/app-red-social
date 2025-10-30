import { Component } from '@angular/core';
import { SidebarUser } from "../../../../shared/layout/sidebar-user/sidebar-user";
import { NavbarUser } from "../../../../shared/layout/navbar-user/navbar-user";
import { CommonModule } from '@angular/common';
import { GoogleService } from '../../../../core/services/google.service';

@Component({
  selector: 'app-perfil-user',
   standalone: true,
  imports: [NavbarUser,CommonModule],
  templateUrl: './perfil-user.html',
  styleUrl: './perfil-user.css'
})
export class PerfilUser {
 backgroundUrl: string = 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80';
isNavActive: boolean = false;
  isLoggedIn: boolean = true;
  usuario: any = null;
  displayName: string = '';

  constructor(private googleService: GoogleService) { } // nombre consistente

  ngOnInit(): void {
    this.loadUsername();
  }

  toggleNav(): void {
    this.isNavActive = !this.isNavActive;
  }

  logout(): void {
    // Aquí implementa tu lógica de cerrar sesión
    console.log('Usuario ha cerrado sesión');
    this.isLoggedIn = false;
    this.usuario = null;
    this.displayName = '';
  }

  loadUsername(): void {
    this.googleService.getCurrentUser().subscribe({
      next: (user) => {
        this.usuario = user;
        // Si username está vacío, usar email
        this.displayName = this.usuario.username?.trim() || this.usuario.email;
        console.log(this.displayName)
        
      },
      error: (err) => {
        console.error('Error al obtener usuario', err);
      }
    });
  }
}
