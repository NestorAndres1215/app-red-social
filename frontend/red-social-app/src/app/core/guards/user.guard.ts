import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { GoogleService } from '../services/google.service';
import { AuthService } from '../services/auth.service';
import { ROLES } from '../constants/roles.contants';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router, private googleService: GoogleService) { }

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    try {
      const token = this.authService.token;
      const user = await firstValueFrom(this.googleService.getCurrentUser());
      const rol = user?.rol?.nombre;

      console.log("🛡️ UserGuard → Usuario:", user);

      if (token && this.googleService.isLoggedIn()) {
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