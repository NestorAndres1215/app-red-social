import { Routes, RouterModule } from '@angular/router';

export const ADMIN_ROUTES: Routes = [

  {
    path: '',
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
    path: 'perfil-admin',
    loadComponent: () => import('../administrador-user/perfil-user-admin/perfil-user-admin.component')
      .then(m => m.PerfilUserAdminComponent)
  },
  {
    path: 'historial-admin',
    loadComponent: () => import('../administrador-user/historial-user-admin/historial-user-admin.component')
      .then(m => m.HistorialUserAdminComponent)
  },
];


