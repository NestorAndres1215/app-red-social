import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-principal.component',
  imports: [],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css',
})
export class PrincipalComponent {

 constructor(private router: Router) { }

  login() {
    this.router.navigate(['/auth/login'])
  }

  register() {
    this.router.navigate(['/auth/registro'])
  }

}
