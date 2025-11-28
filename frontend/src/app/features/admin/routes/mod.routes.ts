import { Routes, RouterModule } from '@angular/router';

export const MOD_ADMIN_ROUTES: Routes = [
    {
        path: 'user-moderador',
        loadComponent: () => import('../moderador-user/mod-user/mod-user.component')
            .then(m => m.ModUserComponent)
    },
    {
        path: 'reg-moderador',
        loadComponent: () => import('../moderador-user/reg-moderador-user/reg-moderador-user.component')
            .then(m => m.RegModeradorUserComponent)
    },

];