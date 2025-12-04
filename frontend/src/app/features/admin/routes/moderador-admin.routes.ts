import { Routes } from '@angular/router';

export const MOD_ADMIN_ROUTES: Routes = [
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
    {
        path: 'rol-permisos-moderador',
        loadComponent: () => import('../moderador-user/roles-permisos/roles-permisos.component')
            .then(m => m.RolesPermisosComponent)
    },
    {
        path: 'buscar-moderador',
        loadComponent: () => import('../moderador-user/buscar-moderador-admin/buscar-moderador-admin.component')
            .then(m => m.BuscarModeradorAdminComponent)
    },
    {
        path: 'auditoria-moderador',
        loadComponent: () => import('../moderador-user/auditoria-moderador-admin/auditoria-moderador-admin.component')
            .then(m => m.AuditoriaModeradorAdminComponent)
    },
    {
        path: 'actividad-moderador',
        loadComponent: () => import('../moderador-user/actividad-moderador-admin/actividad-moderador-admin.component')
            .then(m => m.ActividadModeradorAdminComponent)
    },

    {
        path: 'profile/:codigo/:userParam',
        loadComponent: () => import('../moderador-user/detalle-perfiles-moderador/detalle-perfiles-moderador.component')
            .then(m => m.DetallePerfilesModeradorComponent)
    }
];