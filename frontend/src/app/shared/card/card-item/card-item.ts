import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-item',
  imports: [],
  templateUrl: './card-item.html',
  styleUrl: './card-item.css',
})
export class CardItem {
  @Input() data: string = "0";
  @Input() titulo: string = 'Gráfico de Barras';
}
