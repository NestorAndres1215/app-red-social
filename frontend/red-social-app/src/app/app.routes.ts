import { Routes } from '@angular/router';
import { Principal } from './features/home/principal/principal';
import { AuthCallback } from './features/auth/auth-callback/auth-callback';

export const routes: Routes = [

  { path: '', component: Principal, pathMatch: 'full' },
  { path: 'auth-callback', component: AuthCallback },

  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.routes')
      .then(m => m.AUTH_ROUTES) // <--- CORRECTO AQUÍ
  }
];
