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

        if (!this.authService.isLoggedIn()) {
            return true;
        }

        try {
            const user = await firstValueFrom(this.authService.getCurrentUser());
            const role = user.rol.nombre;

            const destino =
                role === ROLES.ROLE_ADMIN ? '/admin' :
                    role === ROLES.ROLE_MODERADOR ? '/moderador' :
                        role === ROLES.ROLE_USER ? '/inicio' :
                            '/auth/login';

            return this.router.parseUrl(destino);

        } catch (_) {
            return this.router.parseUrl('/auth/login');
        }
    }

}