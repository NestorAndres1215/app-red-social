import { Component } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { AlertService } from '../../../../core/services/alert.service';
import { edadConvertir, filtrarSoloNumeros, obtenerMaxFechaNacimiento } from '../../../../core/utils/validators.utils';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModeradorService } from '../../../../core/services/moderador.service';
import { Router } from '@angular/router';
import { RegisterUser } from '../../../../core/models/register-user.model';
import { MENSAJES } from '../../../../core/constants/mensajes.constants';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reg-moderador-user.component',
 imports: [TittleComponent, ReactiveFormsModule,CommonModule],
  templateUrl: './reg-moderador-user.component.html',
  styleUrl: './reg-moderador-user.component.css',
})
export class RegModeradorUserComponent {
  titulo = 'Registro de Moderador';
  icono = 'fas fa-user-plus';

  
  constructor(
    private alertService: AlertService,
    private moderadorService:   ModeradorService,
    private router: Router,
    private fb: FormBuilder
  ) {

    this.maxFechaNacimiento = obtenerMaxFechaNacimiento(18);
    this.initForm();
  }
  soloNumerosInput(event: any) {
    event.target.value = filtrarSoloNumeros(event.target.value);
  }

  initForm() {
    this.formulario = this.fb.group({
      usuario: ['', Validators.required],
      contrasena: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      correo: ['', Validators.required],
      telefono: ['', Validators.required],
      nacionalidad: ['', Validators.required],
      genero: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
    })
  }
  formulario!: FormGroup
  showPassword = false;
  maxFechaNacimiento!: string;
  generos: string[] = ['Masculino', 'Femenino', 'Otro'];
  nacionalidades: string[] = ['Peru', 'Argentina', 'Chile', 'Colombia', 'Mexico', 'Otro'];


  operar() {
    if (this.formulario.valid) {
      const edad = edadConvertir(this.formulario.value.fechaNacimiento);

      const register: RegisterUser = {
        nombre: this.formulario.value.nombre,
        apellido: this.formulario.value.apellido,
        edad: edad,
        fechaNacimiento: this.formulario.value.fechaNacimiento,
        genero: this.formulario.value.genero,
        nacionalidad: this.formulario.value.nacionalidad,
        username: this.formulario.value.usuario,
        email: this.formulario.value.correo,
        telefono: this.formulario.value.telefono,
        password: this.formulario.value.contrasena
      };

      this.moderadorService.registrar(register).subscribe({
        next: (data: any) => {
          this.alertService.aceptacion(MENSAJES.REGISTRO_EXITOSO_TITULO, MENSAJES.REGISTRO_EXITOSO_MENSAJE);
          this.router.navigate(['/admin/user-admin']);
        },
        error: (error) => {
          this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
        }
      });
    } else {
      this.alertService.advertencia(MENSAJES.CAMPOS_INCOMPLETOS_TITULO, MENSAJES.CAMPOS_INCOMPLETOS_MENSAJE);
      this.formulario.markAllAsTouched();
    }
  }


  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }


}
