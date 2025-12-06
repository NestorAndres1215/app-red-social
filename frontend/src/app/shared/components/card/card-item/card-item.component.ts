import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-card-item',
  imports: [],
  templateUrl: './card-item.component.html',
  styleUrl: './card-item.component.css',
})
export class CardItemComponent {
  @Input() data: string = "0";
  @Input() titulo: string = 'Gráfico de Barras';
}
