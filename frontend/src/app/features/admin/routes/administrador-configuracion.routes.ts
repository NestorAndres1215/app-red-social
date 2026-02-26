import { Routes } from '@angular/router';

export const ADMINISTRADOR_CONFIGURACION: Routes = [
    {
        path: 'administrador-cambiar-contrasena',
        loadComponent: () => import('../administrador-configuracion/administrador-cambiar-contrasena/administrador-cambiar-contrasena')
            .then(m => m.AdministradorCambiarContrasena)
    },
   
    {
        path: 'administrador-privacidad-cuenta',
        loadComponent: () => import('../administrador-configuracion/administrador-privacidad-usuario/administrador-privacidad-usuario')
            .then(m => m.AdministradorPrivacidadUsuario)
    },
     /*
    {
        path: 'cuentas-bloqueadas',
        loadComponent: () => import('../configuracion-admin/cuentas-bloqueadas-admin/cuentas-bloqueadas-admin.component')
            .then(m => m.CuentasBloqueadasAdminComponent)
    },
  */  {
        path: 'administrador-historial-logueo',
        loadComponent: () => import('../administrador-configuracion/administrador-historial-logueo/administrador-historial-logueo')
            .then(m => m.AdministradorHistorialLogueo)
    },
];