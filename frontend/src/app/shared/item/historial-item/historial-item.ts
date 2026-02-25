import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-historial-item',
 imports: [CommonModule],
  templateUrl: './historial-item.html',
  styleUrl: './historial-item.css',
})
export class HistorialItem {

  @Input() item: any = {};
  @Output() viewDetail = new EventEmitter<any>();
  @Output() update = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();

  onViewDetail() { this.viewDetail.emit(this.item); }
  onUpdate() { this.update.emit(this.item); }
  onDelete() { this.delete.emit(this.item); }
}
