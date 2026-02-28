import { Component } from '@angular/core';
import { FilterSelect } from "../../../../shared/filter-select/filter-select";
import { Tabla } from "../../../../shared/tabla/tabla";
import { HistorialItem } from "../../../../shared/item/historial-item/historial-item";
import { AuthService } from '../../../../core/services/auth.service';
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { CommonModule } from '@angular/common';
import { Titulo } from "../../../../shared/titulo/titulo";

@Component({
  selector: 'app-administrador-moderador-auditoria',
  imports: [ CommonModule, Titulo, FilterSelect, HistorialItem],
  templateUrl: './administrador-moderador-auditoria.html',
  styleUrl: './administrador-moderador-auditoria.css',
})
export class AdministradorModeradorAuditoria {
 titulo = 'Auditoria de Moderador';
  icono = 'fas fa-user-secret';


  modulo: (string | 'TODOS')[] = ['TODOS', 'Registro', 'Publicacion', 'Notificacion', 'LoginFallido', 'Login', 'Comentario', 'Mensaje',
    'Like', 'Seguidores', 'ReporteUsuario', 'ConfiguracionPerfil', 'ReportePublicacion'];

  years: (number | 'TODOS')[] = [];

  historialOriginal: any[] = [];
  historial: any[] = [];
  selectedUsername: string = 'TODOS';
  selectedModulo: string = 'TODOS';
  selectedYear: number | 'TODOS' = 'TODOS';
  username: (string | 'TODOS')[] = [];

  constructor(private historiaService: HistorialUsuarioService,private authService:AuthService) { }


  ngOnInit(): void {
    const currentYear = new Date().getFullYear();
    this.years = ['TODOS', ...Array.from({ length: 10 }, (_, i) => currentYear - i)];
    this.selectedYear = currentYear;
    this.cargarHistorial();
    this.cargarUsuario()
  }

 cargarUsuario() {
    this.authService.listarModeradoresLofin().subscribe(data => {
      console.log("Usuarios", data);
    
      this.username = ['TODOS', ...data.map((u: any) => u.username)];
      console.log(this.username)
      this.aplicarFiltros();
    });
  }

  cargarHistorial() {

    this.historiaService.listarHistorialModerador()
      .subscribe(data => {
        console.log(data)
        this.username = data.usuarioHistorial
        this.historialOriginal = data;
        this.aplicarFiltros();
      });
  }
aplicarFiltros() {
  this.historial = this.historialOriginal.filter(item => {
console.log(item)
    const coincideUsername = this.selectedUsername !== 'TODOS'
      ? item.usuarioHistorial === this.selectedUsername
      : true;

    const coincideModulo = this.selectedModulo !== 'TODOS'
      ? String(item.moduloHistorial).toLowerCase() === this.selectedModulo.toLowerCase()
      : true;

    const coincideYear = this.selectedYear !== 'TODOS'
      ? new Date(item.fechaHistorial).getFullYear() === Number(this.selectedYear)
      : true;

    return coincideUsername && coincideModulo && coincideYear;
  });
}


  onModuloChange(value: string | number | null) {
    this.selectedModulo = value !== null ? String(value) : 'TODOS';
    this.aplicarFiltros();
  }

  onYearChange(value: string | number | null) {
    this.selectedYear = value !== null ? (value === 'TODOS' ? 'TODOS' : Number(value)) : 'TODOS';
    this.aplicarFiltros();
  }
onUsernameChange(value: string | number | null) {
  this.selectedUsername = value !== null ? String(value) : 'TODOS';
  this.aplicarFiltros();
}

}
