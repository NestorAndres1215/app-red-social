import { Routes } from "@angular/router";

export const USER_ROUTES: Routes = [

    {
        path: '',
        redirectTo: 'inicio',
        pathMatch: 'full'
    },

    // Login → /auth/login
    {
        path: 'inicio',
        loadComponent: () => import('../principal-user/principal-user.component')
            .then(m => m.PrincipalUserComponent)
    },


];