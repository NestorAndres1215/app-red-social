import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { AlertService } from '../../../core/services/alert.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginAuth } from '../../../core/models/login-auth.model';
import { ROLES } from '../../../core/constants/roles.contants';

@Component({
  selector: 'app-login.component',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {

  formulario!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  ngOnInit(): void {
    this.initForm();
  }


  showPassword = false;

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  initForm() {
    this.formulario = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  registro() {
    this.router.navigate(['/auth/registro']);
  }
  olvidarContrasena() {
    //   this.router.navigate(['/auth/olvidar-contrasena'])
  }

  operar() {
    if (this.formulario.invalid) {
      this.alertService.advertencia(
        'Campos incompletos',
        'Por favor, completa todos los campos requeridos.'
      );
      this.formulario.markAllAsTouched();
      return;
    }

    const login: LoginAuth = {
      login: this.formulario.get('login')?.value,
      password: this.formulario.get('password')?.value,
    };

    this.authService.generateToken(login).subscribe({
      next: (data: any) => {
        // Guardar token
        this.authService.setToken(data.token);

        // Obtener usuario actual
        this.authService.getCurrentUser().subscribe({
          next: (user) => {
            const rol = user.rol?.nombre;

            if (!rol) {
              console.error("El usuario no tiene un rol asignado.");
              return;
            }

            localStorage.setItem('username', user.username);

            switch (rol) {
              case ROLES.ROLE_ADMIN:
                console.log("Ingreso a ADMINISTRADOR");
                this.router.navigate(['/admin']);
                break;

              case ROLES.ROLE_MODERADOR:
                console.log("Ingreso a MODERADOR");
                this.router.navigate(['/moderador']);
                break;

              default:
                console.log("Ingreso a USER");
                this.router.navigate(['/inicio']);
                break;
            }
          },
          error: (error) => {
            console.error('Error obteniendo usuario actual:', error);
          },
          complete: () => {
            console.log('Solicitud de usuario completada');
          }
        });
      },

      error: (error) => {
        console.error('Error generando token:', error);
        this.alertService.error('Error', error.error?.message || 'Error en la autenticación');
      },

      complete: () => {
        console.log('Proceso de autenticación completado');
      }
    });
  }


}
