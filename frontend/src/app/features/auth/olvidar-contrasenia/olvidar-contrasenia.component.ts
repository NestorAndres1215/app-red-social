import { Component, OnInit } from '@angular/core';
import { ButtonComponent } from "../../../shared/components/button/button.component";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CodigoVerificacionService } from '../../../core/services/codigo-verificacion.service';
import { AlertService } from '../../../core/services/alert.service';
import { MENSAJES } from '../../../core/constants/mensajes.constants';

@Component({
  selector: 'app-olvidar-contrasenia',
  imports: [ButtonComponent, ReactiveFormsModule],
  templateUrl: './olvidar-contrasenia.component.html',
  styleUrl: './olvidar-contrasenia.component.css',
})
export class OlvidarContraseniaComponent implements OnInit {
  formulario!: FormGroup;

  constructor(private fb: FormBuilder,private alertService:AlertService, private codigoVerficacion: CodigoVerificacionService) { }

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

    this.codigoVerficacion.verificarCorreo(nombre).subscribe({
      next: (data) => {
        console.log("Respuesta:", data);
      },
      error: (error) => {
        this.alertService.error(MENSAJES.ERROR_TITULO, error.error.message);
      }
    });

  }

}
