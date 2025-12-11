import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { firstValueFrom } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../../core/services/alert.service';
import { ContrasenaAuht } from '../../../core/models/login-auth.model';
import { MENSAJES } from '../../../core/constants/mensajes.constants';
import { ButtonComponent } from '../../../shared/components/button/button.component';

@Component({
  selector: 'app-cambiar-contrasenia',
  imports: [ReactiveFormsModule, ButtonComponent],
  templateUrl: './cambiar-contrasenia.component.html',
  styleUrl: './cambiar-contrasenia.component.css',
})
export class CambiarContraseniaComponent implements OnInit {

  constructor(private router: Router, private auth: AuthService, private fb: FormBuilder, private alertService: AlertService) { }

  formulario!: FormGroup;

  showPassword = false;
  showPasswordNC = false;
  ngOnInit(): void {
    this.cargarUsername()
  }
  correo: string = '';
  initForm() {
    this.formulario = this.fb.group({
      usuario: [{ value: this.usuario, disabled: true }, Validators.required],
      nuevaContra: ['', Validators.required],
      confirmarContra: ['', Validators.required]
    });
  }

  usuario = '';
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
        this.alertService.advertencia("CONTRASEÑA NO COINCIDEN")
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
