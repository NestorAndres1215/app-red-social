import { Component, OnInit } from '@angular/core';
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
export class PerfilUserAdminComponent implements OnInit {

  titulo = 'Perfil de Administradores';
  icono = 'fas fa-user-cog';
  usuario: any = {};
  constructor(private usuarioService: AdministradorService, private authService: AuthService) {

  }
  ngOnInit(): void {
    this.listar()
  }




  listar() {
    this.authService.getCurrentUser().subscribe(
      user => {
        this.usuarioService.listarActual(user.codigo).subscribe(
          data => {
            this.usuario = data;
          },
        );
      },
    );
  }


}
