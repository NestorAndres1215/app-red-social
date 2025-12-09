import { Component, OnInit } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { ButtonComponent } from "../../../../shared/components/button/button.component";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ContrasenaAuht } from '../../../../core/models/login-auth.model';
import { AlertService } from '../../../../core/services/alert.service';
import { AuthService } from '../../../../core/services/auth.service';
import { MENSAJES } from '../../../../core/constants/mensajes.constants';

@Component({
  selector: 'app-cambiar-contrasena-admin',
  imports: [TittleComponent, ReactiveFormsModule, ButtonComponent],
  templateUrl: './cambiar-contrasena-admin.component.html',
  styleUrl: './cambiar-contrasena-admin.component.css',
})
export class CambiarContrasenaAdminComponent implements OnInit {


  titulo = 'Cambiar Contraseña';
  icono = 'fas fa-lock';
  formulario!: FormGroup;
  usuario = '';
  showPassword = false;
  showPasswordNC = false;

  constructor(private router: Router, private auth: AuthService, private fb: FormBuilder, private alertService: AlertService) { }

  ngOnInit(): void {
    this.usuario = localStorage.getItem('username') || '';
    this.initForm();
  }

  initForm() {
    this.formulario = this.fb.group({
      usuario: [{ value: this.usuario, disabled: true }, Validators.required],
      nuevaContra: ['', Validators.required],
      confirmarContra: ['', Validators.required]
    });
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
        next: (resp) => {
          this.alertService.aceptacion(MENSAJES.REGISTRO_EXITOSO_TITULO, MENSAJES.REGISTRO_EXITOSO_MENSAJE);
          this.formulario.reset();
          this.formulario.get('usuario')?.setValue(this.usuario);
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
