import { Component, Input, ViewChild, ElementRef, AfterViewInit, OnChanges } from '@angular/core';
import { Chart, registerables } from 'chart.js';
@Component({
  selector: 'app-pie-chart',
  imports: [],
  templateUrl: './pie-chart.component.html',
  styleUrl: './pie-chart.component.css',
})
export class PieChartComponent implements AfterViewInit, OnChanges {

  @Input() titulo: string = 'Grafico de Porcentaje';
  @Input() labels: string[] = [];
  @Input() data: number[] = [];

  @ViewChild('pieCanvas') pieCanvas!: ElementRef;

  chart!: Chart;

  ngAfterViewInit() {
    this.renderChart();
  }

  ngOnChanges() {
    if (this.chart) {
      this.chart.destroy();
      this.renderChart();
    }
  }

  renderChart() {

    if (!this.pieCanvas) return;

    this.chart = new Chart(this.pieCanvas.nativeElement, {
      type: 'pie',
      data: {
        labels: this.labels,
        datasets: [
          {
            data: this.data, // ← NÚMEROS
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          tooltip: {
            callbacks: {
              label: (context: any) => {
                const label = context.label || '';
                const value = context.raw; // número
                return ` ${value}%`; // ← aquí agregas el %
              }
            }
          }
        }
      }
    });
  }

}