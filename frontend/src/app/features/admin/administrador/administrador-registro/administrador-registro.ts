import { Component } from '@angular/core';
import { Titulo } from "../../../../shared/titulo/titulo";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdministradorService } from '../../../../core/services/administrador.service';
import { AlertService } from '../../../../core/services/alert.service';
import { MENSAJES } from '../../../../core/constants/mensajes';
import { RegisterUser } from '../../../../core/models/register-user.';
import { obtenerMaxFechaNacimiento, filtrarSoloNumeros, edadConvertir } from '../../../../core/utils/validators.utils';
import { CommonModule } from '@angular/common';
import { Button } from "../../../../shared/button/button";

@Component({
  selector: 'app-administrador-registro',
  imports: [Titulo, ReactiveFormsModule, CommonModule, Button],
  templateUrl: './administrador-registro.html',
  styleUrl: './administrador-registro.css',
})
export class AdministradorRegistro {

  constructor(
    private alertService: AlertService,
    private administradorService: AdministradorService,
    private router: Router,
    private fb: FormBuilder
  ) {


  }

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

      this.administradorService.registrar(register).subscribe({
        next: () => {
          this.alertService.aceptacion(MENSAJES.REGISTRO_EXITOSO_TITULO, MENSAJES.REGISTRO_EXITOSO_MENSAJE);
          this.router.navigate(['/admin/mantenimiento-administrador']);
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
