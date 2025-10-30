import { Component } from '@angular/core';
import { NavbarUser } from "../../../shared/layout/navbar-user/navbar-user";
import { SidebarUser } from "../../../shared/layout/sidebar-user/sidebar-user";

@Component({
  selector: 'app-home-user',
    standalone: true,
  imports: [ SidebarUser],
  templateUrl: './home-user.html',
  styleUrl: './home-user.css'
})
export class HomeUser {

}
