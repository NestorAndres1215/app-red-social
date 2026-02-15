import { Routes } from '@angular/router';

export const ADMIN_ROUTES: Routes = [

  {
    path: '',
    loadComponent: () => import('../principal-admin/principal-admin')
      .then(m => m.PrincipalAdmin)
  },
/*  {
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
  {
    path: 'buscar-admin',
    loadComponent: () => import('../administrador-user/buscar-admin/buscar-admin.component')
      .then(m => m.BuscarAdminComponent)
  },
  {
    path: 'acciones-admin',
    loadComponent: () => import('../administrador-user/accion-admin/accion-admin.component')
      .then(m => m.AccionAdminComponent)
  },
  {
    path: 'admin-suspendidos',
    loadComponent: () => import('../administrador-user/admin-suspendidos/admin-suspendidos.component')
      .then(m => m.AdminSuspendidosComponent)
  },
  {
    path: 'profile/:codigo/:userParam',
    loadComponent: () => import('../administrador-user/detalle-perfiles-administradores/detalle-perfiles-administradores.component')
      .then(m => m.DetallePerfilesAdministradoresComponent)
  }*/
];


