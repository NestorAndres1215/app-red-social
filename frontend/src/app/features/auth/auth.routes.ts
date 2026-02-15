import { Routes } from "@angular/router";

export const AUTH_ROUTES: Routes = [

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        loadComponent: () => import('./login/login').then(m => m.Login)
    },
 
    {
        path: 'registro',
        loadComponent: () => import('./registro/registro')
            .then(m => m.Registro)
    },
    {
        path: 'olvidar-contrasena',
        loadComponent: () => import('./olvidar-contrasenia/olvidar-contrasenia')
            .then(m => m.OlvidarContrasenia)
    },
    {
        path: 'codigo-verificacion',
        loadComponent: () => import('./codigo-verificacion-auth/codigo-verificacion-auth')
            .then(m => m.CodigoVerificacionAuth)
    },
    {
        path: 'cambiar-contrasenia',
        loadComponent: () => import('./cambiar-contrasenia/cambiar-contrasenia')
            .then(m => m.CambiarContrasenia)
    },
    {
        path: 'cuenta-bloqueada',
        loadComponent: () => import('./cuenta-bloqueada/cuenta-bloqueada')
            .then(m => m.CuentaBloqueada)
    },

];