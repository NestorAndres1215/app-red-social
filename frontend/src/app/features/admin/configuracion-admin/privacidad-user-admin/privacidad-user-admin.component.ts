import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-privacidad-user-admin',
  imports: [TittleComponent, CommonModule, FormsModule],
  templateUrl: './privacidad-user-admin.component.html',
  styleUrl: './privacidad-user-admin.component.css',
})
export class PrivacidadUserAdminComponent {

  titulo = 'Privacidad de Usuario';
  icono = 'fas fa-lock-open';
  estado = false;

  onToggle(event: any) {
    this.estado = event.target.checked;
    console.log('Switch:', this.estado);
  }


}
