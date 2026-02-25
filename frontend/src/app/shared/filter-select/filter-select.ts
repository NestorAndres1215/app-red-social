import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-filter-select',
  imports: [CommonModule, FormsModule],
  templateUrl: './filter-select.html',
  styleUrl: './filter-select.css',
})
export class FilterSelect {

 @Input() label: string = 'Filtrar por';
  @Input() options: (string | number)[] = [];

@Input() value: string | number | null = null;
@Output() valueChange = new EventEmitter<string | number | null>();

onSelectChange(v: string) {
  this.value = v === '' ? null : v;
  this.valueChange.emit(this.value);
}
}
