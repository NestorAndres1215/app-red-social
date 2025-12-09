import { Component, OnInit } from '@angular/core';
import { TittleComponent } from "../../../../shared/components/tittle/tittle.component";
import { ButtonComponent } from "../../../../shared/components/button/button.component";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ContrasenaAuht } from '../../../../core/models/login-auth.model';
import { AlertService } from '../../../../core/services/alert.service';

@Component({
  selector: 'app-cambiar-contrasena-admin',
  imports: [TittleComponent, ReactiveFormsModule, ButtonComponent],
  templateUrl: './cambiar-contrasena-admin.component.html',
  styleUrl: './cambiar-contrasena-admin.component.css',
})
export class CambiarContrasenaAdminComponent implements OnInit {
  operar() {
    const contrasena: ContrasenaAuht = {
      usuario: this.usuario,
      nuevaContrasena: this.formulario.value.nuevaContra!,
      confirmarContrasena: this.formulario.value.confirmarContra!
    };
 if (contrasena.nuevaContrasena !== contrasena.confirmarContrasena) {
     this.alertService.advertencia("CONTRASEÑA NO COINCIDEN")
    return;
  }

    console.log(contrasena)
  }

  titulo = 'Cambiar Contraseña';
  icono = 'fas fa-lock';
  formulario!: FormGroup;
  usuario = '';

  constructor(private router: Router, private fb: FormBuilder,private alertService:AlertService) { }

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

  showPassword = false;
  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }
  showPasswordNC = false;
  togglePasswordNC(): void {
    this.showPasswordNC = !this.showPasswordNC;
  }
}
