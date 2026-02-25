import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { AdministradorService } from '../../../../core/services/administrador.service';
import { AuthService } from '../../../../core/services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-administrador-perfil',
  imports: [Titulo,CommonModule],
  templateUrl: './administrador-perfil.html',
  styleUrl: './administrador-perfil.css',
})
export class AdministradorPerfil {
  usuario: any = null;
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
            console.log(data)
                  this.usuario = data[0];
          },
        );
      },
    );
  }


}
