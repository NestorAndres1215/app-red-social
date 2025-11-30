import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
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
  @Input() options: (string | number)[] = [];

@Input() value: string | number | null = null;
@Output() valueChange = new EventEmitter<string | number | null>();

onSelectChange(v: string) {
  this.value = v === '' ? null : v;
  this.valueChange.emit(this.value);
}

}
