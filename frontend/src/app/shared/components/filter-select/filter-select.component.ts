import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-filter-select',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './filter-select.component.html',
  styleUrls: ['./filter-select.component.css']
})
export class FilterSelectComponent {

  @Input() label: string = 'Filtrar por';
  @Input() options: string[] = [];   // Lista de opciones
  @Input() value: string = '';       // Valor actual

  @Output() valueChange = new EventEmitter<string>();

  onSelectChange(v: string) {
    this.value = v;
    this.valueChange.emit(this.value);
  }
}
