import { Routes } from '@angular/router';

export const CONFIG_ADMIN_ROUTES: Routes = [
    {
        path: 'cambiar-contrasena',
        loadComponent: () => import('../configuracion-admin/cambiar-contrasena-admin/cambiar-contrasena-admin.component')
            .then(m => m.CambiarContrasenaAdminComponent)
    },
    {
        path: 'privacidad-cuenta',
        loadComponent: () => import('../configuracion-admin/privacidad-user-admin/privacidad-user-admin.component')
            .then(m => m.PrivacidadUserAdminComponent)
    },
    {
        path: 'cuentas-bloqueadas',
        loadComponent: () => import('../configuracion-admin/cuentas-bloqueadas-admin/cuentas-bloqueadas-admin.component')
            .then(m => m.CuentasBloqueadasAdminComponent)
    },
    {
        path: 'historial-login',
        loadComponent: () => import('../configuracion-admin/historial-logueo-admin/historial-logueo-admin.component')
            .then(m => m.HistorialLogueoAdminComponent)
    },
];