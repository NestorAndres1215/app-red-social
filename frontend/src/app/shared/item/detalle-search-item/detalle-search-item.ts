import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdministradorService } from '../../../core/services/administrador.service';
import { Titulo } from "../../titulo/titulo";
import { CardUser } from "../../card/card-user/card-user";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detalle-search-item',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './detalle-search-item.html',
  styleUrl: './detalle-search-item.css',
})
export class DetalleSearchItem {
  @Input() usuarios: any[] = []; 
  @Output() verDetalle = new EventEmitter<any>();

  abrirDetalle(usuario: any) {
    this.verDetalle.emit(usuario);
  }
}
