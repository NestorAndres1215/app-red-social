import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent {

  searchText: string = '';

  @Output() searchChange = new EventEmitter<string>();

  onInputChange(value: string) {
    this.searchText = value;
    this.searchChange.emit(this.searchText);  
  }
}
