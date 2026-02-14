import { Routes } from "@angular/router";

export const AUTH_ROUTES: Routes = [

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        loadComponent: () => import('./login/login.component')
            .then(m => m.LoginComponent)
    },
    {
        path: 'registro',
        loadComponent: () => import('./registro/registro.component')
            .then(m => m.RegistroComponent)
    },
    {
        path: 'olvidar-contrasena',
        loadComponent: () => import('./olvidar-contrasenia/olvidar-contrasenia.component')
            .then(m => m.OlvidarContraseniaComponent)
    },
    {
        path: 'codigo-verificacion',
        loadComponent: () => import('./codigo-verificacion-auth/codigo-verificacion-auth.component')
            .then(m => m.CodigoVerificacionAuthComponent)
    },
    {
        path: 'cambiar-contrasenia',
        loadComponent: () => import('./cambiar-contrasenia/cambiar-contrasenia.component')
            .then(m => m.CambiarContraseniaComponent)
    },
    {
        path: 'cuenta-bloqueada',
        loadComponent: () => import('./cuenta-bloqueada/cuenta-bloqueada.component')
            .then(m => m.CuentaBloqueadaComponent)
    },


];