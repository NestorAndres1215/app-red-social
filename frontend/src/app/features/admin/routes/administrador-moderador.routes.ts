import { Routes } from '@angular/router';

export const ADMINISTRADOR_MODERADOR: Routes = [

    {
        path: 'mantenimiento-moderador',
        loadComponent: () => import('../administrador-moderador/administrador-moderador/administrador-moderador')
            .then(m => m.AdministradorModerador)
    },
    {
        path: 'registro-moderador',
        loadComponent: () => import('../administrador-moderador/administrador-moderador-registro/administrador-moderador-registro')
            .then(m => m.AdministradorModeradorRegistro)
    },
    {
        path: 'busqueda-moderador',
        loadComponent: () => import('../administrador-moderador/administrador-moderador-busqueda/administrador-moderador-busqueda')
            .then(m => m.AdministradorModeradorBusqueda)
    },
    {
        path: 'profile/:codigo/:userParam',
        loadComponent: () => import('../administrador-moderador/administrador-moderador-detalle-perfil/administrador-moderador-detalle-perfil')
            .then(m => m.AdministradorModeradorDetallePerfil)
    },
    {
        path: 'actividad-moderador',
        loadComponent: () => import('../administrador-moderador/administrador-moderador-actividad/administrador-moderador-actividad')
            .then(m => m.AdministradorModeradorActividad)
    },
    {
        path: 'auditoria-moderador',
        loadComponent: () => import('../administrador-moderador/administrador-moderador-auditoria/administrador-moderador-auditoria')
            .then(m => m.AdministradorModeradorAuditoria)
    },


];