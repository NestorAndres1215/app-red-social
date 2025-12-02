import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';
import { ButtonComponent } from "../../button/button.component";
@Component({
  selector: 'app-card-user',
  imports: [CommonModule, MatCardModule, ButtonComponent],
  templateUrl: './card-user.component.html',
  styleUrl: './card-user.component.css',
})
export class CardUserComponent {
  @Input() title!: string;
  @Input() data: any[] = [];

}
