import { Routes } from "@angular/router";

export const ADMINISTRADOR_ROUTES: Routes = [

    {
        path: 'admin',
        loadChildren: () =>
            import('../admin/routes/administrador-usuario.routes')
                .then(m => m.ADMINISTRADOR_USUARIO_ROUTES)
    },

    /*

    {
        path: 'mod-admin',
        loadChildren: () =>
            import('../admin/routes/moderador-admin.routes')
                .then(m => m.MOD_ADMIN_ROUTES)
    },
    {
        path: 'grupos-admin',
        loadChildren: () =>
            import('../admin/routes/grupos-admin.routes')
                .then(m => m.GRUPOS_ADMIN_ROUTES)
    },
  */
    {
        path: 'configuracion-admin',
        loadChildren: () =>
            import('../admin/routes/administrador-configuracion.routes')
                .then(m => m.ADMINISTRADOR_CONFIGURACION)
    }
];