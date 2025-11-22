import { Routes } from '@angular/router';
import { PrincipalComponent } from './features/home/principal/principal.component';
import { Oauth2RedirectComponent } from './features/auth/oauth2-redirect/oauth2-redirect.component';
import { ErrorComponent } from './shared/components/error/error.component';
import { NoAuthGuard } from './core/guards/noauth.guard';
import { HomeUserComponent } from './features/user/home-user/home-user.component';
import { UserGuard } from './core/guards/user.guard';
import { HomeAdminComponent } from './features/admin/home-admin/home-admin.component';
import { AdminGuard } from './core/guards/admin.guard';

export const routes: Routes = [

      {
            path: '',
            component: PrincipalComponent,
            pathMatch: 'full'
      },
      {
            path: 'oauth2/redirect',
            component: Oauth2RedirectComponent
      },
      {
            path: 'error',
            component: ErrorComponent
      },
      {
            path: 'auth',
            loadChildren: () => import('./features/auth/auth.routes').then(m => m.AUTH_ROUTES),
            canActivate: [NoAuthGuard]
      },

      {
            path: '',
            component: HomeUserComponent,
            canActivate: [UserGuard],
            loadChildren: () => import('./features/user/routes/user.routes')
                  .then(m => m.USER_ROUTES)
      },

      {
            path: '',
            component: HomeAdminComponent,
            canActivate: [AdminGuard],
            loadChildren: () => import('./features/admin/routes/admin.routes')
                  .then(m => m.ADMIN_ROUTES)
      },
];
