import { Routes } from "@angular/router";

export const ADMINISTRADOR_ROUTES: Routes = [
    
        {
        path: 'admin',
        loadChildren: () =>
            import('../admin/routes/usuario-admin.routes')
                .then(m => m.ADMIN_ROUTES)
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
    {
        path: 'configuracion-admin',
        loadChildren: () =>
            import('../admin/routes/configuracion-admin.routes')
                .then(m => m.CONFIG_ADMIN_ROUTES)
    }*/
];