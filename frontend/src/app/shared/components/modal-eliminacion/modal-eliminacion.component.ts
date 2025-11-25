import { Component, Inject } from '@angular/core';
import { RespuestaModel } from '../../../core/models/respuesta.model';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-modal-eliminacion',
  imports: [CommonModule,
        MatFormFieldModule,  // <-- Asegúrate de incluir este
    MatInputModule,      // <-- Si usas <input matInput>
    MatDialogModule,     // <-- Si usas diálogos

  ],
  templateUrl: './modal-eliminacion.component.html',
  styleUrl: './modal-eliminacion.component.css',
})
export class ModalEliminacionComponent {
 titulo: string = "";
  subtitulo: string = "";
  razon: string = "";
  meses: any[] = [];
  mesActual: string = "";
  mesCambio: string = "";

  constructor(private dialogRef: MatDialogRef<any>,
    @Inject(MAT_DIALOG_DATA) private data: any,
 
    private sanitizer: DomSanitizer) { }


  ngOnInit(): void {
    console.log("🙌")
    this.titulo = this.data['titulo'];
    this.subtitulo = this.data['subtitulo'];
    this.razon = this.data['razon'];
  

  }

  close(mensaje: string): void {
    const respuesta: RespuestaModel = {
      boton: mensaje,
      razon: this.razon,
    }
  
    this.dialogRef.close(respuesta);
  }
  getHTML(): SafeHtml {
    return  this.sanitizer.bypassSecurityTrustHtml(this.subtitulo + this.mesActual + this.mesCambio);
  }
}
