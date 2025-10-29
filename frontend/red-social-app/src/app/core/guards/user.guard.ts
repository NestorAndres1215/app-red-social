import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { GoogleService } from '../services/google.service';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router,private googleService:GoogleService) {}

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
        if (rol === 'ROLE_USER') {
          console.log("✅ Acceso permitido: USER");
          return true;
        } else if (rol === 'ROLE_ADMIN') {
          console.log("🚫 No es usuario, redirigiendo a dashboard de admin...");
          return this.router.parseUrl('/dashboard-admin'); // 👈 redirección correcta
        }
      }

      console.log("🚫 No logueado, redirigiendo a /login...");
      return this.router.parseUrl('/auth/login');
    } catch (error) {
      console.error('❌ Error en UserGuard:', error);
      return this.router.parseUrl('/auth/login');
    }
  }
}