import { Component, OnInit } from '@angular/core';
import { LayoutUserComponent } from "../layout-user/layout-user.component";
import { RouterModule } from "@angular/router";
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar-user',
  templateUrl: './sidebar-user.component.html',
  styleUrls: ['./sidebar-user.component.css'],
  imports: [LayoutUserComponent, RouterModule, CommonModule]
})
export class SidebarUserComponent implements OnInit {

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
