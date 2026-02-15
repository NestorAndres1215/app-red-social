import { Component } from '@angular/core';
import { Button } from "../../../shared/button/button";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MENSAJES } from '../../../core/constants/mensajes';
import { AlertService } from '../../../core/services/alert.service';
import { CodigoVerificacionService } from '../../../core/services/codigo-verificacion.service';

@Component({
  selector: 'app-olvidar-contrasenia',
  imports: [Button, ReactiveFormsModule],
  templateUrl: './olvidar-contrasenia.html',
  styleUrl: './olvidar-contrasenia.css',
})
export class OlvidarContrasenia {
  formulario!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private alertService: AlertService, private codigoVerficacion: CodigoVerificacionService) { }

  ngOnInit(): void {
    this.initForm()
  }

  initForm() {
    this.formulario = this.fb.group({
      correo: ['', Validators.required],

    });
  }


  olvidarContrasenia() {
    console.log(this.formulario.value.correo)
    const nombre = this.formulario.value.correo;
    localStorage.setItem('correoRecuperacion', nombre);
    this.codigoVerficacion.correoRecuperacion = nombre;
    this.codigoVerficacion.verificarCorreo(nombre).subscribe({
      next: (data) => {
        this.router.navigate(['/auth/codigo-verificacion']);
      },
      error: (error) => {
        this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
      }
    });

  }
  volver() {
    this.router.navigate(['/']);
  }
}
