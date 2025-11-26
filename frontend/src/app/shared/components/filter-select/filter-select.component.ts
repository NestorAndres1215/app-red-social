import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-filter-select',

  imports: [CommonModule, FormsModule],
  templateUrl: './filter-select.component.html',
  styleUrls: ['./filter-select.component.css']
})
export class FilterSelectComponent {

  @Input() label: string = 'Filtrar por';

@Input() options: (string | number)[] = [];   // acepta strings o numbers
@Input() value: string | number = '';         // valor inicial puede ser number
@Output() valueChange = new EventEmitter<string | number>();


  onSelectChange(v: string) {
    this.value = v;
    this.valueChange.emit(this.value);
  }

}
