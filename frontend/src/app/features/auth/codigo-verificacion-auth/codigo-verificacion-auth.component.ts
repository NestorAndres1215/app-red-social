import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';
import { AlertService } from '../../../core/services/alert.service';
import { CodigoVerificacionService } from '../../../core/services/codigo-verificacion.service';
import { ButtonComponent } from "../../../shared/components/button/button.component";
import { MENSAJES } from '../../../core/constants/mensajes.constants';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-codigo-verificacion-auth',
  imports: [ButtonComponent],
  templateUrl: './codigo-verificacion-auth.component.html',
  styleUrl: './codigo-verificacion-auth.component.css',
})
export class CodigoVerificacionAuthComponent {
  async enviar() {
    this.correo = localStorage.getItem('correoRecuperacion') ?? '';
    console.log("Correo recibido:", this.correo);
    await firstValueFrom(this.verificacionService.verificarCorreo(this.correo));

  }
  correo: string = '';

  constructor(
    private router: Router, private mensaje: AlertService,
    private verificacionService: CodigoVerificacionService,
  ) { }


  autoFocusNext(event: any, nextInput: HTMLInputElement) {
    if (event.target.value.length === 1 && nextInput) {
      nextInput.focus();
    }
  }
  async enviarCodigo(n1: string, n2: string, n3: string, n4: string, n5: string, n6: string) {
    console.log(n1 + n2 + n3 + n4 + n5 + n6)
    const codigo = n1 + n2 + n3 + n4 + n5 + n6

    this.verificacionService.verificarCodigo(codigo).subscribe({
      next: () => {
        console.log("entro aqui")
        this.router.navigate(['/auth/cambiar-contrasenia']);
      },
      error: (error) => {
        this.mensaje.error(MENSAJES.ERROR_TITULO, error.error.message);
      }
    });


  }
}
