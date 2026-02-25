import { Component, Input } from '@angular/core';
import { Button } from "../../button/button";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-card-user',
  standalone:true,
  imports: [Button,CommonModule],
  templateUrl: './card-user.html',
  styleUrl: './card-user.css',
})
export class CardUser {
  @Input() title!: string;
  @Input() data: any[] = [];
}
