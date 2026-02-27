import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { CommonModule } from '@angular/common';
import { MatTabsModule } from '@angular/material/tabs';
import { BreakpointObserver } from '@angular/cdk/layout';
import { MatDialog } from '@angular/material/dialog';
import { Estados } from '../../../../core/constants/estados';
import { RespuestaModel } from '../../../../core/models/respuesta';
import { AlertService } from '../../../../core/services/alert.service';
import { ModeradorService } from '../../../../core/services/moderador.service';
import { UsuarioService } from '../../../../core/services/usuario.service';
import { ModalEliminacion } from '../../../../shared/modal-eliminacion/modal-eliminacion';
import { AdministradorActivos } from "../../administrador/administrador-activos/administrador-activos";
import { AdministradorInactivos } from "../../administrador/administrador-inactivos/administrador-inactivos";
import { AdministradorModeradorActivos } from "../administrador-moderador-activos/administrador-moderador-activos";
import { AdministradorModeradorInactivos } from "../administrador-moderador-inactivos/administrador-moderador-inactivos";

@Component({
  selector: 'app-administrador-moderador',
  imports: [Titulo, CommonModule, MatTabsModule, AdministradorModeradorActivos, AdministradorModeradorInactivos],
  templateUrl: './administrador-moderador.html',
  styleUrl: './administrador-moderador.css',
})
export class AdministradorModerador {

  activeTab: number = 0;
  activosLabel: string = 'Activados';
  desactivadosLabel: string = 'Desactivados';

}
