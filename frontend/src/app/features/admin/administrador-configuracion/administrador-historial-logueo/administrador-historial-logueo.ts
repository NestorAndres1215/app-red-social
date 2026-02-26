import { Component } from '@angular/core';
import { HistorialItem } from "../../../../shared/item/historial-item/historial-item";
import { Titulo } from "../../../../shared/titulo/titulo";
import { Estados } from '../../../../core/constants/estados';
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-administrador-historial-logueo',
  imports: [HistorialItem, Titulo,CommonModule],
  templateUrl: './administrador-historial-logueo.html',
  styleUrl: './administrador-historial-logueo.css',
})
export class AdministradorHistorialLogueo {
 historialOriginal: any[] = [];

  constructor(private historiaService: HistorialUsuarioService) { }

  ngOnInit(): void {
    this.cargarHistorial();
  }

  cargarHistorial() {
    const username = localStorage.getItem('username') || '';

    this.historiaService.listarHistorial(username, Estados.ACTIVO)
      .subscribe(data => {
        // Filtro seguro para que siempre encuentre LOGIN
        this.historialOriginal = data.filter((item: any) =>
          item.moduloHistorial?.trim().toUpperCase() === 'LOGIN'
        );

        console.log('Historial filtrado:', this.historialOriginal);
      });
  }

}
