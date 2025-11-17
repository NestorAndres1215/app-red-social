import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { firstValueFrom } from 'rxjs';

import { ROLES } from '../constants/roles.contants';
import { AuthService } from '../services/auth.service';


@Injectable({
    providedIn: 'root'
})
export class NoAuthGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) { }

    async canActivate(): Promise<boolean | UrlTree> {
        if (this.authService.isLoggedIn()) {
            try {
                const user = await firstValueFrom(this.authService.getCurrentUser());
                const role = user?.rol?.name;

                // Definir destino según rol
                const destino: string =
                    role === ROLES.ROLE_ADMIN ? '/admin' :
                        role === ROLES.ROLE_MODERADOR ? '/moderador' :
                            role === ROLES.ROLE_USER ? '/inicio' :
                                '/auth/login';

                // Retornar UrlTree → Angular hace la navegación
                return this.router.parseUrl(destino);

            } catch (error) {
                console.error('Error obteniendo usuario:', error);
                return this.router.parseUrl('/auth/login');
            }
        }

        // No está logueado → puede continuar
        return true;
    }
}