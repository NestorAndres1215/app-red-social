import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";

@Component({
  selector: 'app-actividad-moderador-admin',
  imports: [TittleComponent],
  templateUrl: './actividad-moderador-admin.component.html',
  styleUrl: './actividad-moderador-admin.component.css',
})
export class ActividadModeradorAdminComponent {
  titulo = 'Actividad de Moderador';
icono = 'fas fa-user-cog';   // Icono para Actividad de Moderador

}
