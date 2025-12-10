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
        loadComponent: () => import('./codigo-verificacion-auth.component/codigo-verificacion-auth.component')
            .then(m => m.CodigoVerificacionAuthComponent)
    },

];