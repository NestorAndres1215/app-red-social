import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

   constructor(private router:Router){}

registro() {
 this.router.navigate(['/auth/registro'])
}


   showPassword = false;

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }
}
