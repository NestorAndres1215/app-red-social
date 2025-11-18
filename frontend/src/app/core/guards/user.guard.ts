import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { ROLES } from '../constants/roles.contants';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    try {
      const token = this.authService.token;
      const user = await firstValueFrom(this.authService.getCurrentUser());
      const rol = user?.rol?.nombre;

      console.log("🛡️ UserGuard → Usuario:", user);

      if (token && this.authService.isLoggedIn()) {
        if (rol === ROLES.ROLE_USER) {
          return true;
        } else if (rol === ROLES.ROLE_ADMIN) {

          return this.router.parseUrl('/inicio'); // 👈 redirección correcta
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