import { Component } from '@angular/core';
import { TittleComponent } from '../../../../shared/components/tittle/tittle.component';

@Component({
  selector: 'app-admin-user.component',
  imports: [TittleComponent],
  templateUrl: './admin-user.component.html',
  styleUrl: './admin-user.component.css',
})
export class AdminUserComponent {

  titulo = 'Mantenimiento de Administradores';
  icono = 'fas fa-user-shield';

  
}

