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
  {
    path: 'administrado-perfil',
    loadComponent: () => import('../administrador/administrador-perfil/administrador-perfil')
      .then(m => m.AdministradorPerfil)
  },
  {
    path: 'busqueda-administrador',
    loadComponent: () => import('../administrador/administrador-busqueda/administrador-busqueda')
      .then(m => m.AdministradorBusqueda)
  },
  /* 
    {
      path: 'reg-admin',
      loadComponent: () => import('../administrador-user/reg-user-admin/reg-user-admin.component')
        .then(m => m.RegUserAdminComponent)
    },


  
*/
  {
    path: 'profile/:codigo/:userParam',
    loadComponent: () => import('../administrador/administrador-perfil-detalle/administrador-perfil-detalle')
      .then(m => m.AdministradorPerfilDetalle)
  }
];


