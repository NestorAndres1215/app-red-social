import { Component } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';
import { AlertService } from '../../../core/services/alert.service';
import { CodigoVerificacionService } from '../../../core/services/codigo-verificacion.service';
import { ButtonComponent } from "../../../shared/components/button/button.component";

@Component({
  selector: 'app-codigo-verificacion-auth',
  imports: [ButtonComponent],
  templateUrl: './codigo-verificacion-auth.component.html',
  styleUrl: './codigo-verificacion-auth.component.css',
})
export class CodigoVerificacionAuthComponent {
enviar() {
throw new Error('Method not implemented.');
}

  constructor(
    private loginService: AuthService, private router: Router, private mensaje: AlertService,
    private verificacionService: CodigoVerificacionService
  ) { }
  autoFocusNext(event: any, nextInput: HTMLInputElement) {
    if (event.target.value.length === 1 && nextInput) {
      nextInput.focus();
    }
  }
  async enviarCodigo(n1: string, n2: string, n3: string, n4: string, n5: string, n6: string) {
    console.log(n1 + n2 + n3 + n4 + n5 + n6)
    const codigo = n1 + n2 + n3 + n4 + n5 + n6



  }
}
