import { Component } from '@angular/core';
import { Titulo } from "../../../shared/titulo/titulo";
import { CardItem } from "../../../shared/card/card-item/card-item";

@Component({
  selector: 'app-administrador-principal',
  imports: [Titulo, CardItem],
  templateUrl: './administrador-principal.html',
  styleUrl: './administrador-principal.css',
})
export class AdministradorPrincipal {

}
