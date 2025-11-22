import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule, MatMenuTrigger } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MenuService } from '../../services/menu.service';
@Component({
  selector: 'app-layout-admin',
  imports: [MatMenuTrigger, RouterModule, MatToolbarModule, MatDialogModule, MatIconModule, MatSidenavModule, MatListModule, MatMenuModule, CommonModule],
  templateUrl: './layout-admin.component.html',
  styleUrls: ['./layout-admin.component.css']
})
export class LayoutAdminComponent implements OnInit {

  isLoggedIn = false;
  user: any;
  isRouteActive: boolean = true;
  rolMenu: any
  status = false;
  datosmenuPrimero: any
  menuPrimero: any
  menuSegundo: any
  constructor(
    public authService: AuthService,
    private dialog: MatDialog,
    private router: Router,
    private menuService: MenuService) { }


  ngOnInit(): void {
    this.listarRolMenu()


    this.isLoggedIn = this.authService.isLoggedIn();
    this.user = this.authService.token;
    this.authService.loginStatusSubjec.asObservable().subscribe(
      data => {
        this.isLoggedIn = this.authService.isLoggedIn();
        this.user = this.authService.token;
      }
    )

  }
  async listarRolMenu() {
    this.menuService.obtenerMenusPorDosRoles("ROL00001").subscribe(data => {
      this.rolMenu = data
      console.log(this.rolMenu)
      this.listarMenuPrimero();
      this.listarMenuSegundo('');
    })
  }

  async listarMenuPrimero() {
    this.menuService.obtenerMenuNivel1().subscribe(
      data => {
        this.menuPrimero = data
        console.log(this.menuPrimero)
        this.datosmenuPrimero = this.menuPrimero.filter(
          (item: { rol: { codigo: string } | null }) =>
            item.rol && (item.rol.codigo === 'ROL00001')
        );
        console.log(this.datosmenuPrimero);
      }
    );
  }
  //* Filtrar el menú por categoría y asignar a menu2FiltradoPorCategoria
  menu2FiltradoPorCategoria: { [categoria: string]: any[] } = {};
  toggleSubMenu(menuItem: any): void {
    menuItem.mostrarSubMenu = !menuItem.mostrarSubMenu;
    //* Filtrar el menú por categoría y asignar a menu2FiltradoPorCategoria
    if (menuItem.mostrarSubMenu) {
      console.log(menuItem.mostrarSubMenu)
      //* Filtrar el menú por categoría y asignar a menu2FiltradoPorCategoria
      this.menu2FiltradoPorCategoria[menuItem.categoria] = this.menuSegundo.filter((i: { categoria: any; }) => i.categoria === menuItem.categoria);
      console.log(this.menu2FiltradoPorCategoria[menuItem.categoria])
      if (this.menu2FiltradoPorCategoria[menuItem.categoria].length === 0) {
        this.router.navigate(['/administrador']);
      }
    } else {
      this.menu2FiltradoPorCategoria[menuItem.categoria] = [];
    }
  }


  async listarMenuSegundo(categoria: any) {
    this.menuService.obtenerMenuNivel2().subscribe(
      data => {
        this.menuSegundo = data
      }
    );
  }

  public logout() {
    this.authService.logout();
    window.location.href = '/auth/login';
  }


  addToggle() {
    this.status = !this.status;
  }

  @ViewChild(MatMenuTrigger) mainMenuTrigger!: MatMenuTrigger;
  closeMainMenu() {
    this.mainMenuTrigger.closeMenu();
  }
  tieneSubMenu(menuItem: any): boolean {
    return (
      this.menu2FiltradoPorCategoria[menuItem.categoria] &&
      this.menu2FiltradoPorCategoria[menuItem.categoria].length > 0
    );
  }

  handleClick(menuItem: any): void {
    if (this.tieneSubMenu(menuItem)) {
      menuItem.mostrarSubMenu = !menuItem.mostrarSubMenu;
    } else {
      this.irARuta(menuItem.ruta);
    }
  }
  irARuta(ruta: string): void {
    this.router.navigateByUrl('/' + ruta);
  }

}
