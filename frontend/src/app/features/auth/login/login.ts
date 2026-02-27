import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { Estados } from '../../../core/constants/estados';
import { MENSAJES } from '../../../core/constants/mensajes';
import { ROLES } from '../../../core/constants/roles';

import { LoginAuth } from '../../../core/models/login-auth';
import { AlertService } from '../../../core/services/alert.service';
import { AuthService } from '../../../core/services/auth.service';
import { HistorialUsuarioService } from '../../../core/services/historial-usuario.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Button } from "../../../shared/button/button";
import { HistorialUsuario } from '../../../core/models/historial-usuario';
import { LoginService } from '../../../core/services/login.service';
@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule, Button],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  formulario!: FormGroup;
  showPassword = false;

  constructor(
    private historial: HistorialUsuarioService,
    private fb: FormBuilder,
    private loginService:LoginService,
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  initForm() {
    this.formulario = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  loginWithGoogle(): void {
    this.authService.loginWithGoogle();
  }

  registro() {
    this.router.navigate(['/auth/registro']);
  }

  olvidarContrasena() {
    this.router.navigate(['/auth/olvidar-contrasena'])
  }

  intentosFallidos: number = 0;
  operar() {
    if (this.formulario.invalid) {
      this.alertService.advertencia(MENSAJES.CAMPOS_INCOMPLETOS_TITULO, MENSAJES.CAMPOS_INCOMPLETOS_MENSAJE);
      this.formulario.markAllAsTouched();
      return;
    }
    this.intentosFallidos++;

    const login: LoginAuth = {
      login: this.formulario.value.login,
      password: this.formulario.value.password,
    };

    this.authService.generateToken(login).subscribe({
      next: async (data: any) => {
       
        if (this.intentosFallidos >= 3) {
          await firstValueFrom(this.loginService.bloquear(this.formulario.value.login));
          this.alertService.error("Cuenta bloqueada", "Has superado el número máximo de intentos fallidos. Tu cuenta ha sido bloqueada. Por favor, espera o contacta con el administrador.")
          localStorage.setItem('Bloqueo', 'true');
          return
        }
        this.authService.setToken(data.token);

        this.authService.getCurrentUser().subscribe({
          next: (user) => {
            const rol = user.rol.nombre;
            console.log()

            const estado = user.estadoUsuario.nombre
            if (!rol) {
              return;
            }

            localStorage.setItem('username', user.username);

            switch (estado) {
              case Estados.BLOQUEADO:
                console.log('ESTADO BLOQUEADO');
                this.router.navigate(['auth/cuenta-bloqueada']);
                break;

              case Estados.INACTIVO:
                // lógica para inactivo
                break;

              case Estados.SUSPENDIDO:
                // lógica para suspendido
                break;

              case Estados.PENDIENTE_VERIFICACION:
                // lógica para pendiente
                break;

              case Estados.INHABILITADO:
                // lógica para inhabilitado
                break;

              case Estados.ACTIVO:
                this.registrarLogueo(user.username);

                switch (rol) {
                  case ROLES.ROLE_ADMIN:
                    this.router.navigate(['/administrador']);
                    break;

                  case ROLES.ROLE_MODERADOR:
                    this.router.navigate(['/moderador']);
                    break;

                  default:
                    this.router.navigate(['/inicio']);
                    break;
                }
                break;

              default:
                console.log('Estado no reconocido');
                break;
            }

          },
          error: (error) => {
            console.log(error.error.message)
            this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
          }
        });
      },
      error: (error) => {
        console.log(error.error.message)
        this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
      }
    });
  }

  registrarLogueo(usuario: string) {
    this.loginService.registrarLogueo(usuario).subscribe(() => {
      this.registroHistorial(usuario)
    });
  }


  async registroHistorial(usuario: string) {
    const historial: HistorialUsuario = {
      estado: Estados.ACTIVO,
      detalle: 'Inicio de sesión de esta cuenta',
      titulo: 'INICIO DE SESION',
      modulo: 'LOGIN',
      usuario: usuario
    };

    await firstValueFrom(this.historial.registrar(historial));
  }

}
