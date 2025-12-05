import { Component, OnInit } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { FilterSelectComponent } from "../../../../shared/components/filter-select/filter-select.component";
import { AuthService } from '../../../../core/services/auth.service';
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { BarChartComponent } from "../../../../shared/components/chart/bar-chart/bar-chart.component";
import { CommonModule } from '@angular/common';
import { PieChartComponent } from "../../../../shared/components/chart/pie-chart/pie-chart.component";

@Component({
  selector: 'app-actividad-moderador-admin',
  standalone: true,
  imports: [TittleComponent, BarChartComponent, CommonModule, PieChartComponent],
  templateUrl: './actividad-moderador-admin.component.html',
  styleUrl: './actividad-moderador-admin.component.css',
})
export class ActividadModeradorAdminComponent implements OnInit {

  titulo = 'Actividad de Moderador';
  icono = 'fas fa-user-cog';

  historialOriginal: any[] = [];
  historial: any[] = [];

  labelsModulo: string[] = [];
  dataPorcentaje: number[] = [];

  constructor(
    private historiaService: HistorialUsuarioService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.cargarHistorial();
  }

  cargarHistorial() {
    this.historiaService.listarHistorialModerador().subscribe(data => {
      this.historialOriginal = data;
      this.calcularPorcentajePorModulo();
    });
  }



  calcularPorcentajePorModulo() {

    if (!this.historialOriginal.length) return;

    const total = this.historialOriginal.length;
    const conteoPorModulo: { [key: string]: number } = {};

    this.historialOriginal.forEach(item => {
      const modulo = item.moduloHistorial;
      conteoPorModulo[modulo] = (conteoPorModulo[modulo] || 0) + 1;
    });

    this.labelsModulo = Object.keys(conteoPorModulo);
    this.dataPorcentaje = this.labelsModulo.map(modulo =>
      Number(((conteoPorModulo[modulo] / total) * 100).toFixed(2))
    );

  }
}
