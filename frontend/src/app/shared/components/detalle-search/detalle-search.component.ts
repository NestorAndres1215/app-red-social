import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-detalle-search',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalle-search.component.html',
  styleUrls: ['./detalle-search.component.css'],
})
export class DetalleSearchComponent {
  @Input() usuarios: any[] = []; 
  @Output() verDetalle = new EventEmitter<any>();

  abrirDetalle(usuario: any) {
    this.verDetalle.emit(usuario);
  }
}
