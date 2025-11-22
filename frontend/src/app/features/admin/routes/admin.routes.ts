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

];


