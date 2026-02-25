import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-administrador-accion',
  imports: [Titulo,CommonModule],
  templateUrl: './administrador-accion.html',
  styleUrl: './administrador-accion.css',
})
export class AdministradorAccion {
 titulo = 'Acciones del Administrador';
  icono = 'fas fa-user-gear';
  acciones = [
    {
      titulo: "Ver historial del usuario",
      descripcion: "Advertencias, suspensiones y reportes previos"
    },
    {
      titulo: "Advertir usuario",
      descripcion: "Registra una advertencia en el historial"
    },
    {
      titulo: "Suspender usuario",
      descripcion: "Suspensión temporal o permanente según el caso"
    },
    {
      titulo: "Reactivar usuario",
      descripcion: "Restaura el acceso de cuentas suspendidas"
    },
    {
      titulo: "Forzar cierre de sesión",
      descripcion: "Finaliza todas las sesiones activas del usuario"
    },
    {
      titulo: "Bloqueo temporal",
      descripcion: "Evita el inicio de sesión por un periodo corto"
    },
    {
      titulo: "Gestionar sesiones activas",
      descripcion: "Cerrar sesiones específicas sin cerrar todas"
    },
    {
      titulo: "Asignar etiqueta de riesgo",
      descripcion: "Marcar al usuario como bajo riesgo o sospechoso"
    },
    {
      titulo: "Registrar nota interna",
      descripcion: "Notas solo visibles para el equipo de administración"
    }
  ];
}
