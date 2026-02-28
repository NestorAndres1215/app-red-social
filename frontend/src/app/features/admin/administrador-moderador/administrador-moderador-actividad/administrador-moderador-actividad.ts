import { Component } from '@angular/core';
import { CardItem } from "../../../../shared/card/card-item/card-item";
import { Titulo } from "../../../../shared/titulo/titulo";
import { PieChart } from '../../../../shared/chart/pie-chart/pie-chart';
import { BarChart } from '../../../../shared/chart/bar-chart/bar-chart';
import { EstadisticasService } from '../../../../core/services/estadisticas.service';
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';

@Component({
  selector: 'app-administrador-moderador-actividad',
  imports: [CardItem, Titulo,PieChart,BarChart],
  templateUrl: './administrador-moderador-actividad.html',
  styleUrl: './administrador-moderador-actividad.css',
})
export class AdministradorModeradorActividad {
 titulo = 'Actividad de Moderador';
  icono = 'fas fa-user-cog';

  tituloBarChart = 'Porcentaje de Actividad por Módulo'
  tituloPieChart = 'Porcentaje por Módulo'

  historialOriginal: any[] = [];
  historial: any[] = [];

  labelsModulo: string[] = [];
  dataPorcentaje: number[] = [];
  iconoE= 'fas fa-user-cog';
  constructor(
    private estadisticasService: EstadisticasService,
  ) { }

  ngOnInit(): void {

    this.cargarTotalModerador();
    this.cargarTotalModeradorActivos();
    this.cargaPorcentajeHistorial();
  }

  totalModerador = "";
  tituloTotalModerador = "Total de Moderadores";

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
