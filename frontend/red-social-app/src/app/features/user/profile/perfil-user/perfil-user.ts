import { Component } from '@angular/core';
import { SidebarUser } from "../../../../shared/layout/sidebar-user/sidebar-user";
import { NavbarUser } from "../../../../shared/layout/navbar-user/navbar-user";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-perfil-user',
   standalone: true,
  imports: [NavbarUser,CommonModule],
  templateUrl: './perfil-user.html',
  styleUrl: './perfil-user.css'
})
export class PerfilUser {
 backgroundUrl: string = 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&auto=format&fit=crop&w=1500&q=80';

}
