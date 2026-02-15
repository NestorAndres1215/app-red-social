import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LayoutUser } from "../layout-user/layout-user";

@Component({
  selector: 'app-sidebar-user',
  imports: [CommonModule, RouterModule, LayoutUser],
  templateUrl: './sidebar-user.html',
  styleUrl: './sidebar-user.css',
})
export class SidebarUser {
isNavActive: boolean = false;
  isLoggedIn: boolean = true;
  usuario: any = null;
  displayName: string = '';
  nombre: String = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.loadUsername();
  }

  toggleNav(): void {
    this.isNavActive = !this.isNavActive;
  }

  logout(): void {
    console.log('Usuario ha cerrado sesión');
    this.isLoggedIn = false;
    this.usuario = null;
    this.displayName = '';
  }

  loadUsername(): void {
    this.authService.getCurrentUser().subscribe(
      user => {
        this.usuario = user;
        this.displayName = this.usuario.username?.trim() || this.usuario.email;
      });
  }
}
