import { Component } from '@angular/core';
import { NavbarUser } from "../navbar-user/navbar-user";
import { RouterModule } from "@angular/router";
import { GoogleService } from '../../../core/services/google.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar-user',
  imports: [NavbarUser, RouterModule, CommonModule],
  templateUrl: './sidebar-user.html',
  styleUrl: './sidebar-user.css'
})
export class SidebarUser {
  isNavActive: boolean = false;
  isLoggedIn: boolean = true;
  usuario: any = null;
  displayName: string = '';
  nombre: String = '';

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
