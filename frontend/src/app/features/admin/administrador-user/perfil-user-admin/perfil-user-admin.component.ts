import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";

@Component({
  selector: 'app-perfil-user-admin',
  imports: [TittleComponent],
  templateUrl: './perfil-user-admin.component.html',
  styleUrl: './perfil-user-admin.component.css',
})
export class PerfilUserAdminComponent {
  titulo = 'Perfil de Administradores';
  icono = 'fas fa-user-cog';

}
