import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-layout-user',
  imports: [RouterModule, CommonModule],
  templateUrl: './layout-user.component.html',
  styleUrls: ['./layout-user.component.css']
})
export class LayoutUserComponent implements OnInit {

  isNavActive: boolean = false;
  isLoggedIn: boolean = true;
  usuario: any;
  displayName!: string;

  constructor(private authService: AuthService) { }
  ngOnInit(): void {
    this.loadUsername();
  }

  toggleNav(): void {
    this.isNavActive = !this.isNavActive;
  }

  loadUsername(): void {
    this.authService.getCurrentUser().subscribe(
      user => {
        this.usuario = user;
        this.displayName = this.usuario.username.trim() || this.usuario.email;
      },
    );
  }
  logout(): void {
    this.isLoggedIn = false;
    this.usuario = null;
    this.displayName = '';
  }

}
