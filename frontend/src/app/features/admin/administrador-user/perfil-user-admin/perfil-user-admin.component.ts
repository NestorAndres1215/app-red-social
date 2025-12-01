import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { UsuarioService } from '../../../../core/services/usuario.service';
import { AuthService } from '../../../../core/services/auth.service';
import { AdministradorService } from '../../../../core/services/administrador.service';

@Component({
  selector: 'app-perfil-user-admin',
  imports: [TittleComponent],
  templateUrl: './perfil-user-admin.component.html',
  styleUrl: './perfil-user-admin.component.css',
})
export class PerfilUserAdminComponent {
  titulo = 'Perfil de Administradores';
  icono = 'fas fa-user-cog';
  constructor(private usuarioService: AdministradorService, private authService:AuthService) { 
    this.listar()
  }
usuario: any = {};
listar() {
  this.authService.getCurrentUser().subscribe({
    next: user => {
      console.log(user)
      this.usuarioService.listarActual(user.codigo).subscribe({
        next: data => {
          this.usuario = data;
          console.log("Usuario cargado:", data);
        },
        error: err => {
          console.error("Error en listarActual:", err);
        }
      });
    },
    error: err => {
      console.error("Error getCurrentUser:", err);
    }
  });
}


}
