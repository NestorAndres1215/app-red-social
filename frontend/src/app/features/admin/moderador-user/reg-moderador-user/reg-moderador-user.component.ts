import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";

@Component({
  selector: 'app-reg-moderador-user.component',
  imports: [TittleComponent],
  templateUrl: './reg-moderador-user.component.html',
  styleUrl: './reg-moderador-user.component.css',
})
export class RegModeradorUserComponent {
  titulo = 'Registro de Moderador';
  icono = 'fas fa-user-plus';
}
