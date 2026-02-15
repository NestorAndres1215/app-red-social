import { Component } from '@angular/core';
import { Titulo } from "../../../shared/titulo/titulo";
import { CardItem } from "../../../shared/card/card-item/card-item";

@Component({
  selector: 'app-principal-admin',
  imports: [Titulo, CardItem],
  templateUrl: './principal-admin.html',
  styleUrl: './principal-admin.css',
})
export class PrincipalAdmin {

}
