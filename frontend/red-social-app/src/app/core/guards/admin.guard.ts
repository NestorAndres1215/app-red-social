import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { GoogleService } from '../services/google.service';
import { AuthService } from '../services/auth.service';
import { ROLES } from '../constants/roles.contants';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private authService: AuthService, private googleService: GoogleService, private router: Router) { }

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    try {
      const token = this.authService.token;
      const user = await firstValueFrom(this.googleService.getCurrentUser());
      const rol = user?.role?.name;

      if (token && this.googleService.isLoggedIn()) {
        if (rol === ROLES.ROLE_ADMIN) {
          return true;
        } else if (rol === ROLES.ROLE_USER) {
          return this.router.parseUrl('/inicio'); // 👈 AQUÍ la corrección
        } else if (rol === ROLES.ROLE_MODERADOR) {
          return this.router.parseUrl('/moderador');
        }
      }
      return this.router.parseUrl('/auth/login');
    } catch (error) {

      return this.router.parseUrl('/auth/login');
    }
  }
}