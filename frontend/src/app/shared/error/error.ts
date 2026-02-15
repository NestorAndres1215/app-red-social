import { Component } from '@angular/core';
import { Button } from "../button/button";
import { Router } from '@angular/router';

@Component({
  selector: 'app-error',
  imports: [Button],
  templateUrl: './error.html',
  styleUrl: './error.css',
})
export class Error {


  constructor(private router: Router) { }

  volver() {
    this.router.navigate(['/'])
  }
}
