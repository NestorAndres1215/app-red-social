import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { AdministradorInactivos } from "../administrador-inactivos/administrador-inactivos";
import { AdministradorActivos } from "../administrador-activos/administrador-activos";

@Component({
  selector: 'app-administrador-user',
  imports: [Titulo, CommonModule, MatTabsModule, AdministradorInactivos, AdministradorActivos],
  templateUrl: './administrador-user.html',
  styleUrl: './administrador-user.css',
})
export class AdministradorUser {
  activosLabel: string = 'Activados';
  desactivadosLabel: string = 'Desactivados';
  activeTab: number = 0;
}
