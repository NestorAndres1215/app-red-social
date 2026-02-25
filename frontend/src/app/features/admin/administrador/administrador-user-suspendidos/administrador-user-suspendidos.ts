import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { MatTabsModule } from '@angular/material/tabs';
import { CommonModule } from '@angular/common';
import { AdministradorSuspendidos } from '../administrador-suspendidos/administrador-suspendidos';
import { AdministradorInhabilitados } from '../administrador-inhabilitados/administrador-inhabilitados';
import { AdministradorBloqueados } from '../administrador-bloqueados/administrador-bloqueados';

@Component({
  selector: 'app-administrador-user-suspendidos',
  imports: [
    Titulo,
    MatTabsModule,
    CommonModule,
    AdministradorInhabilitados,
    AdministradorBloqueados,
    AdministradorSuspendidos  
  ],
  templateUrl: './administrador-user-suspendidos.html',
  styleUrls: ['./administrador-user-suspendidos.css'],
})
export class AdministradorUserSuspendidos {
  suspendidosLabel: string = 'Suspendidos';
  inhabilitadoLabel: string = 'Inhabilitado';
  bloqueadoLabel: string = 'Bloqueado';
  activeTab: number = 0;
}