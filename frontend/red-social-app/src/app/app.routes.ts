import { Routes } from '@angular/router';
import { Principal } from './features/home/principal/principal';
import { AuthCallback } from './features/auth/auth-callback/auth-callback';
import { HomeUser } from './features/user/home-user/home-user';
import { UserGuard } from './core/guards/user.guard';
import { HomeModerador } from './features/moderador/home-moderador/home-moderador';
import { Error } from './shared/error/error';
import { NoAuthGuard } from './core/guards/noauth.guard';

export const routes: Routes = [

  { path: '', component: Principal, pathMatch: 'full' },
  { path: 'auth-callback', component: AuthCallback },
  { path: 'error', component: Error },
  {
    path: 'auth',
    canActivate: [NoAuthGuard],
    loadChildren: () => import('./features/auth/auth.routes')
      .then(m => m.AUTH_ROUTES) // <--- CORRECTO AQUÍ
  },
  {
    path: '',
    component: HomeUser,
    canActivate: [UserGuard],
    loadChildren: () => import('./features/user/routes/user.routes')
      .then(m => m.USER_ROUTES) // <--- CORRECTO AQUÍ

  },
  {
    path: 'profile/:codigo/:userParam',
    canActivate: [UserGuard],
    loadComponent: () => import('./features/user/profile/perfil-user/perfil-user')
      .then(m => m.PerfilUser)
  }

];
