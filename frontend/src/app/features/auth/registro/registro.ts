import { Component } from '@angular/core';
import { MENSAJES } from '../../../core/constants/mensajes';
import { RegisterUser } from '../../../core/models/register-user.model';
import { edadConvertir, filtrarSoloNumeros, obtenerMaxFechaNacimiento } from '../../../core/utils/validators.utils';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../../core/services/alert.service';
import { UsuarioService } from '../../../core/services/usuario.service';
import { CommonModule } from '@angular/common';
import { Button } from "../../../shared/button/button";

@Component({
  selector: 'app-registro',
 imports: [CommonModule, ReactiveFormsModule, Button],
  templateUrl: './registro.html',
  styleUrl: './registro.css',
})
export class Registro {

constructor(
    private alertService: AlertService,
    private usuarioService: UsuarioService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  formulario!: FormGroup
  showPassword = false;
  maxFechaNacimiento!: string;
  generos: string[] = ['Masculino', 'Femenino', 'Otro'];
  nacionalidades: string[] = ['Peru', 'Argentina', 'Chile', 'Colombia', 'Mexico', 'Otro'];

  ngOnInit(): void {
    this.maxFechaNacimiento = obtenerMaxFechaNacimiento(18);
    this.initForm();
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  login() {
    this.router.navigate(['/auth/login']);
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

      this.usuarioService.registrar(register).subscribe({
        next: (data: any) => {
          this.alertService.aceptacion(MENSAJES.REGISTRO_EXITOSO_TITULO, MENSAJES.REGISTRO_EXITOSO_MENSAJE);
          this.router.navigate(['/auth/login']);
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
}
