import { Routes } from "@angular/router";

export const ADMINISTRADOR_ROUTES: Routes = [

    {
        path: 'administrador',
        loadChildren: () =>
            import('../admin/routes/administrador-usuario.routes')
                .then(m => m.ADMINISTRADOR_USUARIO_ROUTES)
    },

    {
        path: 'administrador-moderador',
        loadChildren: () =>
            import('../admin/routes/administrador-moderador.routes')
                .then(m => m.ADMINISTRADOR_MODERADOR)
    },
    /*

    {
        path: 'grupos-admin',
        loadChildren: () =>
            import('../admin/routes/grupos-admin.routes')
                .then(m => m.GRUPOS_ADMIN_ROUTES)
    },
  */
    {
        path: 'administrador-configuracion',
        loadChildren: () =>
            import('../admin/routes/administrador-configuracion.routes')
                .then(m => m.ADMINISTRADOR_CONFIGURACION)
    }
];