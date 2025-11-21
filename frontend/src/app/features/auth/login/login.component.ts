import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { AlertService } from '../../../core/services/alert.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { LoginAuth } from '../../../core/models/login-auth.model';
import { ROLES } from '../../../core/constants/roles.contants';
import { MENSAJES } from '../../../core/constants/mensajes.constants';

@Component({
  selector: 'app-login.component',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {

  formulario!: FormGroup;
  showPassword = false;

  constructor(
    private fb: FormBuilder,
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
    this.authService.loginWithGoogle(); // redirige al backend
  }

  registro() {
    this.router.navigate(['/auth/registro']);
  }

  olvidarContrasena() {
    //   this.router.navigate(['/auth/olvidar-contrasena'])
  }

  operar() {
    if (this.formulario.invalid) {
      this.alertService.advertencia(MENSAJES.CAMPOS_INCOMPLETOS_TITULO, MENSAJES.CAMPOS_INCOMPLETOS_MENSAJE);
      this.formulario.markAllAsTouched();
      return;
    }

    const login: LoginAuth = {
      login: this.formulario.get('login')?.value,
      password: this.formulario.get('password')?.value,
    };

    this.authService.generateToken(login).subscribe({
      next: (data: any) => {
        this.authService.setToken(data.token);

        this.authService.getCurrentUser().subscribe({
          next: (user) => {
            const rol = user.rol?.nombre;

            if (!rol) {
              return;
            }

            localStorage.setItem('username', user.username);

            switch (rol) {
              case ROLES.ROLE_ADMIN:
                this.router.navigate(['/admin']);
                break;

              case ROLES.ROLE_MODERADOR:
                this.router.navigate(['/moderador']);
                break;

              default:
                this.router.navigate(['/inicio']);
                break;
            }
          },
          error: (error) => {
            this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
          }
        });
      },
      error: (error) => {
        this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
      }
    });
  }

}
