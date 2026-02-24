import { Component } from '@angular/core';
import { Button } from "../../../shared/button/button";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { MENSAJES } from '../../../core/constants/mensajes';
import { ContrasenaAuht } from '../../../core/models/login-auth';
import { AlertService } from '../../../core/services/alert.service';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-cambiar-contrasenia',
  imports: [Button,ReactiveFormsModule],
  templateUrl: './cambiar-contrasenia.html',
  styleUrl: './cambiar-contrasenia.css',
})
export class CambiarContrasenia {

  constructor(private router: Router, private auth: AuthService, private fb: FormBuilder, private alertService: AlertService) { }

  formulario!: FormGroup;
  correo: string = '';
  showPassword = false;
  showPasswordNC = false;
  usuario = '';

  ngOnInit(): void {
    this.cargarUsername()
  }

  initForm() {
    this.formulario = this.fb.group({
      usuario: [{ value: this.usuario, disabled: true }, Validators.required],
      nuevaContra: ['', Validators.required],
      confirmarContra: ['', Validators.required]
    });
  }

  async cargarUsername() {
    this.correo = localStorage.getItem('correoRecuperacion') ?? '';
    const lista = await firstValueFrom(this.auth.listarLogin());
    const usuario = lista.find((item: any) => item.email === this.correo);
    this.usuario = usuario.username;
    this.initForm()

  }


  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }
  togglePasswordNC(): void {
    this.showPasswordNC = !this.showPasswordNC;
  }

  operar() {
    if (this.formulario.valid) {
      const contrasena: ContrasenaAuht = {
        usuario: this.usuario,
        nuevaContrasena: this.formulario.value.nuevaContra!,
        confirmarContrasena: this.formulario.value.confirmarContra!
      };

      if (contrasena.nuevaContrasena !== contrasena.confirmarContrasena) {
        this.alertService.advertencia(MENSAJES.CONTRASENIA_NO_COINCIDE)
        return;
      }
      this.auth.actualizarCambioContrasenia(contrasena).subscribe({
        next: () => {
          this.alertService.aceptacion(MENSAJES.REGISTRO_EXITOSO_TITULO, MENSAJES.REGISTRO_EXITOSO_MENSAJE);
          this.router.navigate(['/auth/login']);
        },
        error: (error) => {
          this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
        }
      });
    }
    else {
      this.alertService.advertencia(MENSAJES.CAMPOS_INCOMPLETOS_TITULO, MENSAJES.CAMPOS_INCOMPLETOS_MENSAJE);
      this.formulario.markAllAsTouched();
    }

  }
}
