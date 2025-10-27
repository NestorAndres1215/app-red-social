import { Routes } from "@angular/router";

export const AUTH_ROUTES: Routes = [

    // Cuando entren a /auth → redirige a /auth/login
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },

    // Login → /auth/login
    {
        path: 'login',
        loadComponent: () => import('./login/login')
            .then(m => m.Login)
    },

    // Registro → /auth/register
    {
        path: 'registro',
        loadComponent: () => import('./registro/registro')
            .then(m => m.Registro)
    }

];
