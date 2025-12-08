import { Component, OnInit } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { BarChartComponent } from "../../../../shared/components/chart/bar-chart/bar-chart.component";
import { CommonModule } from '@angular/common';
import { PieChartComponent } from "../../../../shared/components/chart/pie-chart/pie-chart.component";
import { CardItemComponent } from "../../../../shared/components/card/card-item/card-item.component";
import { EstadisticasService } from '../../../../core/services/estadisticas.service';

@Component({
  selector: 'app-actividad-moderador-admin',
  standalone: true,
  imports: [TittleComponent, CommonModule, PieChartComponent, CardItemComponent, BarChartComponent],
  templateUrl: './actividad-moderador-admin.component.html',
  styleUrl: './actividad-moderador-admin.component.css',
})
export class ActividadModeradorAdminComponent implements OnInit {

  titulo = 'Actividad de Moderador';
  icono = 'fas fa-user-cog';

  tituloBarChart = 'Porcentaje de Actividad por Módulo'
  tituloPieChart = 'Porcentaje por Módulo'

  historialOriginal: any[] = [];
  historial: any[] = [];

  labelsModulo: string[] = [];
  dataPorcentaje: number[] = [];

  constructor(
    private estadisticasService: EstadisticasService,
    private historiaService: HistorialUsuarioService,
  ) { }

  ngOnInit(): void {

    this.cargarTotalModerador();
    this.cargarTotalModeradorActivos();
    this.cargaPorcentajeHistorial();
  }

  totalModerador = "";
  tituloTotalModerador = "Total de Usuarios Moderadores";

  tituloTotalModeradorActivos = "Total de Usuarios Activos";
  totalActivos = "";
  porcentajeActivos: number = 0;
  atributoActivos: string = '';

  tituloTotalModeradorInactivos = "Total de Usuarios Inactivos";
  totalInactivos = "";
  porcentajeInactivos: number = 0;
  atributoInactivos: string = '';

  cargarTotalModerador() {
    this.estadisticasService.listarTotalCantidadModerador().subscribe(data => {
      this.totalModerador = data[0]?.total ?? 0;
    });
  }

  cargarTotalModeradorActivos() {
    this.estadisticasService.listarPorcentajeEstadoModerador().subscribe(data => {
      const itemActivo = data.find((x: any) => x.atributo === 'ACTIVO') ?? null;
      this.totalActivos = itemActivo ? (itemActivo.total) : "";
      this.porcentajeActivos = itemActivo ? Number(itemActivo.porcentaje) : 0;
      this.atributoActivos = itemActivo ? itemActivo.atributo : 'SIN DATOS';

      const itemInactivo = data.find((x: any) => x.atributo === 'INACTIVO') ?? null;
      this.totalInactivos = itemInactivo ? itemInactivo.total : 0;
      this.porcentajeInactivos = itemInactivo ? itemInactivo.porcentaje : 0;
      this.atributoInactivos = itemInactivo ? itemInactivo.atributo : '';
    });
  }
  listaHistorial: any[] = [];
  labelsHistorial: string[] = [];
  dataHistorial: number[] = [];

  cargaPorcentajeHistorial() {
    this.estadisticasService.listarPorcentajeModulosModerador().subscribe((data: any[]) => {

      this.listaHistorial = data;
      this.labelsHistorial = data.map(item => item.atributo);
      this.dataHistorial = data.map(item =>
        Number(item.porcentaje.replace('%', '').trim())
      );
      console.log(this.dataHistorial)
    });
  }









}


