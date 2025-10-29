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

  constructor(private authService: AuthService,private googleService:GoogleService, private router: Router) {}

  async canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean | UrlTree> {
    try {
      const token = this.authService.token;
      const user = await firstValueFrom(this.googleService.getCurrentUser());
      const rol = user?.role?.name;

      console.log("🛡️ AdminGuard → Usuario:", user);

      if (token && this.googleService.isLoggedIn()) {
        if (rol === ROLES.ROLE_ADMIN) {
          console.log("✅ Acceso permitido: ADMIN");
          return true;
        } else if (rol === ROLES.ROLE_USER) {
          console.log("🚫 No es admin, redirigiendo a dashboard de usuario...");
          return this.router.parseUrl('/inicio'); // 👈 AQUÍ la corrección
        }
      }

      console.log("🚫 No logueado, redirigiendo a /login...");
      return this.router.parseUrl('/auth/login');
    } catch (error) {
      console.error('❌ Error en AdminGuard:', error);
      return this.router.parseUrl('/auth/login');
    }
  }
}