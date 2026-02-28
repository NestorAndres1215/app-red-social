import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../../../core/services/alert.service';
import { ModeradorService } from '../../../../core/services/moderador.service';
import { edadConvertir, filtrarSoloNumeros, obtenerMaxFechaNacimiento } from '../../../../core/utils/validators.utils';
import { MENSAJES } from '../../../../core/constants/mensajes';
import { RegisterUser } from '../../../../core/models/register-user.';
import { Button } from "../../../../shared/button/button";

@Component({
  selector: 'app-administrador-moderador-registro',
  imports: [Titulo, ReactiveFormsModule, CommonModule, Button],
  templateUrl: './administrador-moderador-registro.html',
  styleUrl: './administrador-moderador-registro.css',
})
export class AdministradorModeradorRegistro {

  constructor(
    private alertService: AlertService,
    private moderadorService: ModeradorService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
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
    if (this.formulario.invalid) {
      this.alertService.advertencia(MENSAJES.CAMPOS_INCOMPLETOS_TITULO, MENSAJES.CAMPOS_INCOMPLETOS_MENSAJE);
      this.formulario.markAllAsTouched();
      return;
    }

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
      next: () => {
        this.alertService.aceptacion(MENSAJES.REGISTRO_EXITOSO_TITULO, MENSAJES.REGISTRO_EXITOSO_MENSAJE);
        this.router.navigate(['/administrador-moderador/mantenimiento-moderador']);
      },
      error: (error) => {
        this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
      }
    });

  }


  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }
}
