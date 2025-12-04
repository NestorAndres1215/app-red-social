import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { CardUserComponent } from "../../../../shared/components/card/card-user/card-user.component";
import { ActivatedRoute } from '@angular/router';
import { ModeradorService } from '../../../../core/services/moderador.service';

@Component({
  selector: 'app-detalle-perfiles-moderador',
  imports: [TittleComponent, CardUserComponent],
  templateUrl: './detalle-perfiles-moderador.component.html',
  styleUrl: './detalle-perfiles-moderador.component.css',
})
export class DetallePerfilesModeradorComponent {

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
