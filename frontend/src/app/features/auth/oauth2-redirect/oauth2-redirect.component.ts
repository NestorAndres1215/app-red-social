import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-oauth2-redirect.component',
  imports: [],
  templateUrl: './oauth2-redirect.component.html',
  styleUrl: './oauth2-redirect.component.css',
})
export class Oauth2RedirectComponent {
  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    const token = this.route.snapshot.queryParamMap.get('token');
  
    
    if (token) {
      this.authService.setToken(token);          // guarda token en localStorage
      this.router.navigate(['/inicio'], { replaceUrl: true }); // redirige limpio
    } else {
      this.router.navigate(['/login']);
    }
  }
}
