import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";

@Component({
  selector: 'app-historial-user-admin',
  imports: [TittleComponent],
  templateUrl: './historial-user-admin.component.html',
  styleUrl: './historial-user-admin.component.css',
})
export class HistorialUserAdminComponent {
  titulo = 'Historial de Administrador';
  icono = 'fas fa-history';

}
