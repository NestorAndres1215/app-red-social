import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";

@Component({
  selector: 'app-auditoria-moderador-admin',
  imports: [TittleComponent],
  templateUrl: './auditoria-moderador-admin.component.html',
  styleUrl: './auditoria-moderador-admin.component.css',
})
export class AuditoriaModeradorAdminComponent {

  titulo = 'Auditoria de Moderador';
  icono = 'fas fa-user-secret';

}
