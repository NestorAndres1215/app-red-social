import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { CardUser } from "../../../../shared/card/card-user/card-user";
import { ActivatedRoute } from '@angular/router';
import { ModeradorService } from '../../../../core/services/moderador.service';

@Component({
  selector: 'app-administrador-moderador-detalle-perfil',
  imports: [Titulo, CardUser],
  templateUrl: './administrador-moderador-detalle-perfil.html',
  styleUrl: './administrador-moderador-detalle-perfil.css',
})
export class AdministradorModeradorDetallePerfil {

  titulo: string = "";
  codigo: string = "";
  icono = 'fas fa-user-lock';

  usuario: any;
  userData: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private administradorService: ModeradorService
  ) { }

  ngOnInit(): void {
    this.codigo = this.route.snapshot.paramMap.get('codigo') ?? "";
    this.listarActual(this.codigo);
  }

  listarActual(codigo: string) {
    this.administradorService.listarUsuarioCodigo(codigo).subscribe(
      data => {
        this.usuario = data;
        this.titulo = `${data.nombre} ${data.apellido}`;
        this.userData = this.buildUserData(data);
      },

    );
  }

  buildUserData(data: any) {
    return [
      { label: 'Nombre', value: data.nombre },
      { label: 'Apellido', value: data.apellido },
      { label: 'Correo', value: data.login.email },
      { label: 'Username', value: data.login.username },
      { label: 'Teléfono', value: data.login.telefono },
      { label: 'Edad', value: data.edad },
      { label: 'Fecha de nacimiento', value: data.fechaNacimiento },
      { label: 'Género', value: data.genero },
      { label: 'Nacionalidad', value: data.nacionalidad }
    ];
  }

}
