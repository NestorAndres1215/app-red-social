import { Routes, RouterModule } from '@angular/router';

export const ADMIN_ROUTES: Routes = [

  {
    path: '',
    redirectTo: 'admin',
    pathMatch: 'full'
  },
  {
    path: 'admin',
    loadComponent: () => import('../principal-admin/principal-admin.component')
      .then(m => m.PrincipalAdminComponent)
  },
  {
    path: 'user-admin',
    loadComponent: () => import('../administrador-user/admin-user/admin-user.component')
      .then(m => m.AdminUserComponent)
  },
  {
    path: 'reg-admin',
    loadComponent: () => import('../administrador-user/reg-user-admin/reg-user-admin.component')
      .then(m => m.RegUserAdminComponent)
  },
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


