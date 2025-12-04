import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";

@Component({
  selector: 'app-roles-permisos',
  imports: [TittleComponent],
  templateUrl: './roles-permisos.component.html',
  styleUrl: './roles-permisos.component.css',
})
export class RolesPermisosComponent {

  titulo = 'Roles y Permisos';
  icono = 'fas fa-user-lock';


}
