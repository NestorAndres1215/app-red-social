
import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';


@Component({
  selector: 'app-table',

  imports: [CommonModule, NgxPaginationModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
})
export class TableComponent {
  @Input() icono: string = '';
  @Input() titulo: string = '';
  @Input() columnas: { etiqueta: string; clave: string }[] = [];

  @Input() onRegistrar!: () => void;
  @Input() botonesConfig: any = {};
  @Input() datos: any[] = [];

  paginaActual: number = 1;
  itemsPorPagina: number = 10;

  // 🔥 Variables para ordenar
  columnaOrden: string = '';
  direccionOrden: 'asc' | 'desc' = 'asc';

  @Input() onVer!: (fila: any) => void;
  @Input() onActualizar!: (fila: any) => void;
  @Input() onEliminar!: (fila: any) => void;
  @Input() onImprimir!: (fila: any) => void;

  hasActionButtons(): boolean {
    return (
      this.botonesConfig.actualizar ||
      this.botonesConfig.eliminar ||
      this.botonesConfig.imprimir ||
      this.botonesConfig.ver
    );
  }

  // Obtener valores de campos anidados -> usuario.nombre
  obtenerValor(obj: any, clave: string): any {
    return clave.split('.').reduce((valor, parte) => valor?.[parte], obj);
  }

  // 🔥 ORDENAMIENTO ASC / DESC POR COLUMNA
  ordenarPor(campo: string) {
    if (this.columnaOrden === campo) {
      this.direccionOrden = this.direccionOrden === 'asc' ? 'desc' : 'asc';
    } else {
      this.columnaOrden = campo;
      this.direccionOrden = 'asc';
    }

    this.datos.sort((a, b) => {
      const valorA = this.obtenerValor(a, campo);
      const valorB = this.obtenerValor(b, campo);

      if (valorA == null) return 1;
      if (valorB == null) return -1;

      if (typeof valorA === 'number' && typeof valorB === 'number') {
        return this.direccionOrden === 'asc' ? valorA - valorB : valorB - valorA;
      }

      return this.direccionOrden === 'asc'
        ? valorA.toString().localeCompare(valorB.toString())
        : valorB.toString().localeCompare(valorA.toString());
    });
  }
}
