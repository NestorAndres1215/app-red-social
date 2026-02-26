import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-administrador-privacidad-usuario',
  imports: [Titulo,CommonModule, FormsModule],
  templateUrl: './administrador-privacidad-usuario.html',
  styleUrl: './administrador-privacidad-usuario.css',
})
export class AdministradorPrivacidadUsuario {
  estado = false;

  onToggle(event: any) {
    this.estado = event.target.checked;
    console.log('Switch:', this.estado);
  }

}
