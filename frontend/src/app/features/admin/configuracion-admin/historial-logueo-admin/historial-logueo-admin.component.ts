import { Component, OnInit } from '@angular/core';
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { Estados } from '../../../../core/constants/estados.contants';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { HistorialItemComponent } from "../../../../shared/components/item/historial-item/historial-item.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-historial-logueo-admin',
  imports: [TittleComponent, HistorialItemComponent,CommonModule],
  templateUrl: './historial-logueo-admin.component.html',
  styleUrl: './historial-logueo-admin.component.css',
})
export class HistorialLogueoAdminComponent implements OnInit {

  titulo = 'Historial de Inicio de Sesión';
  icono = 'fas fa-history';
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
