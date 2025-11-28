import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent {
  @Input() label: string = '';
  @Input() color: string = 'primary'; // primary, secondary, success, danger, warning...
  @Input() size: string = 'md'; // sm | md | lg
  @Input() icon: string = ''; // ej: 'fas fa-save'
  @Input() iconPosition: 'left' | 'right' = 'left';
  @Input() block: boolean = false; // full width
  @Input() loading: boolean = false;
  @Input() disabled: boolean = false;

  @Output() onClick = new EventEmitter<void>();

  handleClick() {
    if (!this.disabled && !this.loading) {
      this.onClick.emit();
    }
  }
}
