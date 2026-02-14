import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav, MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuTrigger } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../services/auth.service';
import { MenuService } from '../../services/menu.service';
import { Subscription } from 'rxjs';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-layout-admin',
  templateUrl: './layout-admin.component.html',
  styleUrls: ['./layout-admin.component.css'],
  standalone: true,
  imports: [CommonModule, MatSidenavModule, FormsModule, MatDividerModule,
    MatListModule, MatIconModule, MatToolbarModule, RouterModule
  ]
})
export class LayoutAdminComponent implements OnInit {

  isLoggedIn = false;
  user: any;
  rolMenu: any;
  status = false;
  username!: string;
  datosmenuPrimero: any[] = [];
  menuPrimero: any[] = [];
  menuSegundo: any[] = [];
  menu2FiltradoPorCategoria: { [categoria: string]: any[] } = {};
  private loginSub!: Subscription;
  @ViewChild(MatMenuTrigger) mainMenuTrigger!: MatMenuTrigger;
  @ViewChild('sidenav') sidenav!: MatSidenav;


  constructor(
    private authService: AuthService,
    private router: Router,
    private menuService: MenuService
  ) { }

  ngOnInit(): void {
    this.listarRolMenu();
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = localStorage.getItem('username') || '';
    this.loginSub = this.authService.loginStatusSubjec.asObservable().subscribe(() => {
      this.isLoggedIn = this.authService.isLoggedIn();
    });
  }

  ngOnDestroy(): void {
    if (this.loginSub) this.loginSub.unsubscribe();
  }

  async listarRolMenu() {
    this.menuService.obtenerMenusPorDosRoles("ROL00001").subscribe(data => {
      this.rolMenu = data;
      this.listarMenuPrimero();
      this.listarMenuSegundo();
    });
  }

  async listarMenuPrimero() {
    this.menuService.obtenerMenuNivel1().subscribe(data => {
      this.menuPrimero = data;
      this.datosmenuPrimero = this.menuPrimero.filter(
        (item: any) => item.rol && item.rol.codigo === 'ROL00001'
      );
    });
  }

  async listarMenuSegundo() {
    this.menuService.obtenerMenuNivel2().subscribe(data => {
      this.menuSegundo = data;
    });
  }


  handleClick(menuItem: any): void {
    const codigoObjetivo = 'ME000196';

    if (menuItem.codigo === codigoObjetivo) {
      this.logout();
    }

    if (this.menuSegundo?.some(i => i.categoria === menuItem.categoria)) {
      menuItem.mostrarSubMenu = !menuItem.mostrarSubMenu;

      this.menu2FiltradoPorCategoria[menuItem.categoria] = this.menuSegundo
        .filter(i => i.categoria === menuItem.categoria);
    }
    else if (menuItem.menuRuta) {
      this.sidenav.close();
      this.irARuta(menuItem.menuRuta);
    }
  }

  toggleSubMenu(menuItem: any): void {
    menuItem.mostrarSubMenu = !menuItem.mostrarSubMenu;

    if (menuItem.mostrarSubMenu) {
      this.menu2FiltradoPorCategoria[menuItem.categoria] = this.menuSegundo
        .filter(i => i.categoria === menuItem.categoria && i.nivel === 2);
    } else {
      this.menu2FiltradoPorCategoria[menuItem.categoria] = [];
    }
  }

  tieneSubMenu(menuItem: any): boolean {
    return (
      this.menu2FiltradoPorCategoria[menuItem.categoria] &&
      this.menu2FiltradoPorCategoria[menuItem.categoria].length > 0
    );
  }

  irARuta(ruta: string | undefined): void {
    this.router.navigateByUrl('/' + ruta);
    this.sidenav.close();
  }

  logout() {
    console.log("SE CERRO SESION")
    this.authService.logout()
  }

  addToggle() {
    this.status = !this.status;
  }

  closeMainMenu() {
    this.mainMenuTrigger.closeMenu();
  }

}
