import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { ButtonComponent } from "../../../../shared/components/button/button.component";

@Component({
  selector: 'app-accion-admin',
  imports: [TittleComponent, ButtonComponent],
  templateUrl: './accion-admin.component.html',
  styleUrl: './accion-admin.component.css',
})
export class AccionAdminComponent {
  titulo = 'Acciones del Administrador';
  icono = 'fas fa-user-gear';

}
