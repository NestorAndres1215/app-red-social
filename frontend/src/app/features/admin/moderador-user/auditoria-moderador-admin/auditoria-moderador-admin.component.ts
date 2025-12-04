import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { HistorialUsuarioService } from '../../../../core/services/historial-usuario.service';
import { FilterSelectComponent } from "../../../../shared/components/filter-select/filter-select.component";
import { HistorialItemComponent } from "../../../../shared/components/item/historial-item/historial-item.component";
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../../core/services/auth.service';

@Component({
  selector: 'app-auditoria-moderador-admin',
  imports: [CommonModule,TittleComponent, FilterSelectComponent, HistorialItemComponent],
  templateUrl: './auditoria-moderador-admin.component.html',
  styleUrl: './auditoria-moderador-admin.component.css',
})
export class AuditoriaModeradorAdminComponent {

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
      // Mapeamos SOLO los usernames
      this.username = ['TODOS', ...data.map((u: any) => u.username)];
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
