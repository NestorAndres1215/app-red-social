
import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-historial-item',
  imports: [CommonModule],
  templateUrl: './historial-item.component.html',
  styleUrl: './historial-item.component.css',
})
export class HistorialItemComponent {
  // Input para recibir los datos del historial
  @Input() item: any = {};
  // Outputs para acciones
  @Output() viewDetail = new EventEmitter<any>();
  @Output() update = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();

  onViewDetail() { this.viewDetail.emit(this.item); }
  onUpdate() { this.update.emit(this.item); }
  onDelete() { this.delete.emit(this.item); }
}
