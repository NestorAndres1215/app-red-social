import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { ROLES } from '../constants/roles';

@Injectable({
    providedIn: 'root'
})
export class ModeradorGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) { }

    async canActivate(): Promise<boolean | UrlTree> {
        try {
            const token = this.authService.token;
            const user = await firstValueFrom(this.authService.getCurrentUser());
            const rol = user.role.nombre;

            if (token && this.authService.isLoggedIn()) {
                if (rol === ROLES.ROLE_MODERADOR) {
                    return true;
                } else if (rol === ROLES.ROLE_USER) {
                    return this.router.parseUrl('/inicio');
                } else if (rol === ROLES.ROLE_ADMIN) {
                    return this.router.parseUrl('/admin');
                }
            }
            return this.router.parseUrl('/auth/login');
        } catch (error) {
            return this.router.parseUrl('/auth/login');
        }
    }
}