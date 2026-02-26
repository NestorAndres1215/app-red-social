import { Routes } from '@angular/router';

import { NoAuthGuard } from './core/guards/noauth.guard';
import { UserGuard } from './core/guards/user.guard';

import { AdminGuard } from './core/guards/admin.guard';
import { HomeUser } from './features/user/home-user/home-user';
import { Principal } from './features/home/principal/principal';
import { Error } from './shared/error/error';
import { Oauth2Redirect } from './features/auth/oauth2-redirect/oauth2-redirect';
import { AdministradorHome } from './features/admin/administrador-home/administrador-home';

export const routes: Routes = [

  {
    path: '',
    component: Principal,
    pathMatch: 'full'
  },
  {
    path: 'error',
    component: Error
  },

  {
    path: 'auth',
    loadChildren: () =>
      import('./features/auth/auth.routes')
        .then(m => m.AUTH_ROUTES),
    canActivate: [NoAuthGuard]
  },
  {
    path: 'oauth2/redirect',
    component: Oauth2Redirect
  },

  
  

    {
      path: '',
      component: HomeUser,
      canActivate: [UserGuard],
      loadChildren: () =>
        import('./features/user/routes/user.routes')
          .then(m => m.USER_ROUTES)
    },
  
  

  {
    path: '',
    component: AdministradorHome,
    canActivate: [AdminGuard],
    loadChildren: () =>
      import('./features/routes/administradores.routes')
        .then(m => m.ADMINISTRADOR_ROUTES)
  },
];
