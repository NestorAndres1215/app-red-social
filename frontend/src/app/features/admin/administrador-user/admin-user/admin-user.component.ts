import { Component } from '@angular/core';
import { TittleComponent } from '../../../../shared/components/tittle/tittle.component';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from "../../../../shared/components/button/button.component";

@Component({
  selector: 'app-admin-user',
  
  imports: [TittleComponent, CommonModule],
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css'],
})
export class AdminUserComponent {

  titulo = 'Mantenimiento de Administradores';
  icono = 'fas fa-user-shield';


}

