import { Component } from '@angular/core';
import { RegisterUser } from '../../../core/models/register-user.model';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { edadConvertir, filtrarSoloNumeros, obtenerMaxFechaNacimiento } from '../../../core/utils/validators.utils';
import { AlertService } from '../../../core/services/alert.service';
import { UsuarioService } from '../../../core/services/usuario.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registro.component',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css',
})
export class RegistroComponent {


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

  }
}
