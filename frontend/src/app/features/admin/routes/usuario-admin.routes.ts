import { Routes } from '@angular/router';

export const ADMIN_ROUTES: Routes = [

  {
    path: '',
    loadComponent: () => import('../principal-admin/principal-admin')
      .then(m => m.PrincipalAdmin)
  },
  {
    path: 'mantenimiento-administrador',
    loadComponent: () => import('../administrador/administrador-user/administrador-user')
      .then(m => m.AdministradorUser)
  },
  {
    path: 'administrador-suspendidos',
    loadComponent: () => import('../administrador/administrador-user-suspendidos/administrador-user-suspendidos')
      .then(m => m.AdministradorUserSuspendidos)
  },
  {
    path: 'administrador-acciones',
    loadComponent: () => import('../administrador/administrador-accion/administrador-accion')
      .then(m => m.AdministradorAccion)
  },
  {
    path: 'administrador-historial',
    loadComponent: () => import('../administrador/administrador-historial/administrador-historial')
      .then(m => m.AdministradorHistorial)
  },
  /* 
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
      path: 'profile/:codigo/:userParam',
      loadComponent: () => import('../administrador-user/detalle-perfiles-administradores/detalle-perfiles-administradores.component')
        .then(m => m.DetallePerfilesAdministradoresComponent)
    }*/
];


