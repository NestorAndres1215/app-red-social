import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdministradorService } from '../../../../core/services/administrador.service';
import { Titulo } from "../../../../shared/titulo/titulo";
import { CardUser } from "../../../../shared/card/card-user/card-user";

@Component({
  selector: 'app-administrador-perfil-detalle',
  imports: [Titulo, CardUser],
  templateUrl: './administrador-perfil-detalle.html',
  styleUrl: './administrador-perfil-detalle.css',
})
export class AdministradorPerfilDetalle {

  titulo: string = "";
  codigo: string = "";
  icono = 'fas fa-user-lock';

  usuario: any;
  userData: any[] = []; 

  constructor(
    private route: ActivatedRoute,
    private administradorService: AdministradorService
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
