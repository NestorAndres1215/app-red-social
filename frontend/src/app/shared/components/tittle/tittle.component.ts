import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-tittle',
  imports: [],

  templateUrl: './tittle.component.html',
  styleUrl: './tittle.component.css',
})
export class TittleComponent {
  @Input() title: string = 'Título por defecto';
  @Input() icon: string = 'fas fa-crown'; // Icono por defecto
}
